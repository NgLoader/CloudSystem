package de.ng.cloud.master.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import de.ng.cloud.core.util.FileUtil;

public class CloudConfig {
	
	public static final CloudConfig CONFIG = new CloudConfig();
	
	private boolean useCloudDatabase;
	private boolean storePlayerData;
	
	private String cloudAddress;
	private int cloudPort;
	
	private String language;
	
	public void load() {
		File file = new File("./Cloud/cloud.properties");
		
		if(!new File("./Cloud").exists())
			file.mkdirs();

		if(!file.exists())
			try {
				System.out.println("Cloud settings not found. generating...");
				FileUtil.copyLocalResourceFileToFolder("cloud/settings/cloud.properties", "cloud.properties", new File("./Cloud"));
			} catch (Exception ex) {
				System.out.println("Failed to create file \"" + file.getPath() + ".");
				ex.printStackTrace();
			}
		
		try {
			Properties properties = new Properties();
			FileInputStream inputStream = new FileInputStream(file);
			properties.load(inputStream);
			inputStream.close();
			
			this.useCloudDatabase = Boolean.valueOf(getPropertiesValue(properties, "useCloudDatabase"));
			this.storePlayerData = Boolean.valueOf(getPropertiesValue(properties, "storePlayerData"));
			this.cloudAddress = getPropertiesValue(properties, "cloudAddress");
			this.cloudPort = Integer.valueOf(getPropertiesValue(properties, "cloudPort"));
			this.language = getPropertiesValue(properties, "language");
			
		} catch (Exception ex) {
			System.out.println("Failed to load file \"" + file.getPath() + ".");
			ex.printStackTrace();
		}
	}
	
	private String getPropertiesValue(Properties properties, String key) {
		if(properties.containsKey(key))
			return properties.getProperty(key);
		throw new NullPointerException("Properties key not found: key=\"" + key + "\"");
	}
	
	public int getCloudPort() {
		return cloudPort;
	}
	
	public String getCloudAddress() {
		return cloudAddress;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public boolean isStorePlayerData() {
		return storePlayerData;
	}
	
	public boolean isUseCloudDatabase() {
		return useCloudDatabase;
	}
}