package DataFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CSVConverter {

	CSVConverter() throws IOException
	{
		Properties p = new Properties();
		FileReader fr = new FileReader("src/config.properties");
		p.load(fr);
		
		FileReader frOut = new FileReader(p.getProperty("outputFilePath-FormattedJSON"));
		BufferedReader br = new BufferedReader(frOut);
		FileOutputStream out = new FileOutputStream(p.getProperty("outputFilePath-CSV"));
		String line, header="";
		int[] a = {0,0,0,0};
		boolean start=false;
		while((line=br.readLine()) != null){
			System.out.println("--"+line);
			if(line.startsWith("\"issues\":")){
				start=true;
				System.out.println(line);
			}
			if(start==true){
				if(line.endsWith("{"))
					a[0]++;
				if(line.endsWith("["))
					a[1]++;
				if((line.endsWith("}")) || (line.endsWith("},")))
					a[0]--;
				if((line.endsWith("]")) || (line.endsWith("],")))
					a[1]--;
				if(line.startsWith("\"key\"")){
					header=header+line;
					System.out.println(header);
				}
				
			}
		}
		if(start==false){
			System.out.println("There are no issues");
		}
	}
}
