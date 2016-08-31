package com.appsfreedom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Initializer {
	public static Properties obj_prop;
	public static Properties data_prop;
	
	public static Properties loadPropFile(){
		if(data_prop==null){
			FileInputStream fis_object;
			FileInputStream fis_data;
			try {
				//fis_object = new FileInputStream(new File(System.getProperty("user.dir")+"\\resources\\objects.properties"));
				fis_data = new FileInputStream(new File(System.getProperty("user.dir")+"\\resources\\data.properties"));
				//obj_prop= new Properties();
				data_prop = new Properties();
				//obj_prop.load(fis_object);
				data_prop.load(fis_data);
			} catch (FileNotFoundException e) {
				//System.out.println("object.properties file not found");
				System.out.println("Fail : object.properties file not found");
				
			} catch (IOException e) {
				System.out.println("Fail : Unable to load object.properties file");
			}
			
		}
		
		return data_prop;
	}
}
