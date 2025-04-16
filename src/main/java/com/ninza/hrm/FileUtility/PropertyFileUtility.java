package com.ninza.hrm.FileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility 
{
	public String getDataFromPropertiesFile(String key) throws Exception
	{
		FileInputStream fis= new FileInputStream("./config-env-Data/configEnvData.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String data = pobj.getProperty(key);
		
		return data;
		
	}
}
