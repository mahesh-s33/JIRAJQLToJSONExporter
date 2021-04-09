package DataFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import WriterFactory.HTMLConverter;
import ConnectionFactory.ConnectionClass;

public class Loader {

	public static void main(String[] args) throws MalformedURLException, IOException {

		Scanner s=new Scanner(System.in);
		System.out.println("Loading the new Configuration and Input files...");
		
		//calling the connectionClass method.
		ConnectionClass cc = new ConnectionClass();
		cc.connectionMethod();

		//Calling the JSON->JSONViewable form method
		JSONFormatter jf = new JSONFormatter();
		
		//Calling the JSONViewable->HTML method
/*		HTMLConverter hw = new HTMLConverter();*/
		
		//Calling the JSONViewable->CSV method
		CSVConverter csv = new CSVConverter();
		
	}

}
