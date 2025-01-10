package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Readpropertyfile {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties");
		Properties prop = new Properties();
		prop.load(fr);
		
		System.out.println(prop.getProperty("browser"));
		System.out.println(prop.getProperty("testurl"));
		
		System.out.println(prop.getProperty("backpackImage"));
		System.out.println(prop.getProperty("bikeLightImage"));
		System.out.println(prop.getProperty("tshirtImage"));
		System.out.println(prop.getProperty("jacketImage"));
		System.out.println(prop.getProperty("onesieImage"));
		System.out.println(prop.getProperty("redshirtImage"));
		
	}
	
}
