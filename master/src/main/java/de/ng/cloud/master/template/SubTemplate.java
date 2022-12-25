package de.ng.cloud.master.template;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SubTemplate {
	
	private File folder;
	
	private String name;
	private Properties properties;
	
	public SubTemplate(File folder) {
		this.folder = folder;
	}
	
	public boolean loadSubTemplate() {
		if(!folder.exists())
			folder.mkdir();
		
		if(new File(folder.getPath() + "/template.properties").exists())
			try {
				this.name = folder.getName();
				
				if(properties == null)
					properties = new Properties();
				
				FileInputStream inputStream = new FileInputStream(folder.getPath() + "/template.properties");
				properties.load(inputStream);
				inputStream.close();
				return true;
			} catch (Exception ex) {
				System.out.println("Failed to load file \"" + folder.getPath() + "/template.properties\".");
				ex.printStackTrace();
			}
		return false;
	}
	
	public File getFolder() {
		return folder;
	}
	
	public String getName() {
		return name;
	}
	
	public Properties getProperties() {
		return properties;
	}
}