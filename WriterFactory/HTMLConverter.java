package WriterFactory;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HTMLConverter
{
	public HTMLConverter() throws IOException
	{
		Properties confProp =new Properties();
		FileReader fr1 =  new FileReader("src/config.properties");
		confProp.load(fr1);
		
		//writing JSON in viewable form into HTML.
		FileReader fr3 =  new FileReader(confProp.getProperty("outputFilePath-FormattedJSON"));
		BufferedReader br3 = new BufferedReader(fr3);
		FileOutputStream finalOut = new FileOutputStream(confProp.getProperty("outputFilePath-HTML"));
		
		String html = "<html>", htmlFoot= "</html>", space="", line;
		while((line=br3.readLine()) != null){
			if(line.endsWith("{") || line.endsWith("[")){
				space=space+"&emsp;";
			}
			if(line=="}" || line=="}," || line=="]" || line=="],"){
				space=space.substring(0,space.length()-6);
			}
			if(line != ""){
			html = html + "<p>" + space + line + "</p>";
			finalOut.write(html.getBytes());
			}
		}
		finalOut.write(htmlFoot.getBytes());
	}
}	