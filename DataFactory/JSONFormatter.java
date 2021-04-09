package DataFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JSONFormatter {
	
	JSONFormatter() throws IOException{
		
		Properties confProp =new Properties();
		FileReader fr1 =  new FileReader("src/config.properties");
		confProp.load(fr1);
		
		//writing JSON string into viewable form.
		FileReader fr2 =  new FileReader(confProp.getProperty("outputFilePath-RAW"));
		BufferedReader br2 = new BufferedReader(fr2);
		FileOutputStream intOut = new FileOutputStream(confProp.getProperty("outputFilePath-FormattedJSON"));
		
	/*		FileReader fr2 = new FileReader("src/Output/outputFile.txt");
		BufferedReader br = new BufferedReader(fr2);
		FileOutputStream out = new FileOutputStream("src/Output/outputFile-IntermediateHTML.html");*/
	
		String line;
		while((line=br2.readLine()) != null ){
			line = line.replaceAll(",",",\n");
			line = line.replaceAll("\\[","[\n");
			line = line.replaceAll("\\{","{\n");
			line = line.replaceAll("\\]","\n]\n");
			line = line.replaceAll("\\}","\n}\n");
			line = line.replaceAll("\n\n","\n");
			line = line.replaceAll("}\n,\n","},\n");
			line = line.replaceAll("]\n,\n","],\n");
			intOut.write((line+"\n").getBytes());
		}
	}
}
