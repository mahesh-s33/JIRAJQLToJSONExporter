package ConnectionFactory;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ConnectionClass {

		public void connectionMethod() throws MalformedURLException, IOException
		{

			//reading the config file
			Properties confProp = new Properties();
			FileReader configReader = new FileReader("src/config.properties");
			confProp.load(configReader);
			System.out.println(confProp);
			
			//reading the input file
			Properties inp = new Properties();
			FileReader inputReader = new FileReader(confProp.getProperty("inputFilePath"));
			inp.load(inputReader);
			System.out.println(inp);

			//Creating the connection object
			String credentials = inp.getProperty("Username") + ":" + inp.getProperty("Password");
			String jqlUrl = inp.getProperty("JQL");
			URL url = new URL(jqlUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			String basicAuth = "Basic "+ javax.xml.bind.DatatypeConverter.printBase64Binary(credentials.getBytes());
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseMessage());
			}
			
			//Writing the Raw JSON into an output file
			FileOutputStream out = new FileOutputStream(confProp.getProperty("outputFilePath-RAW"));
			InputStreamReader is = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(is);
			String output;
			System.out.println("Writing the Raw JSON into an output file");
			while ((output = br.readLine()) != null) {
				out.write(output.getBytes());
			}

			conn.disconnect();
	}
}
