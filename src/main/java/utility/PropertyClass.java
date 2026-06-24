package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyClass {

	Properties prop = new Properties();

	public PropertyClass() throws IOException {
		InputStream file = null;
		
		if(System.getProperty("env") == null) {
			file = new FileInputStream("QA.properties");
		}
		else if (System.getProperty("env").equals("staging")) {
			file = new FileInputStream("staging.properties");
		}
		
		prop.load(file);
	}
	
	public String getUrl() {
		return prop.getProperty("URL");
	}
}
