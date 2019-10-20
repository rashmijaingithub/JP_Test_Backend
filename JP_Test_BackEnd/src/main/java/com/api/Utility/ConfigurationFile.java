package com.api.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationFile {

	Properties pro =new Properties();


	public  String getPropertyValue(String propkey) throws Exception
	{
		File src=new File("Configuration/Config.property");
		FileInputStream fis=new FileInputStream(src);
		//Properties pro=new Properties();
		pro.load(fis);
		String value=pro.getProperty(propkey);
		//System.out.println("application url " +url);
		return value;
	}

}
