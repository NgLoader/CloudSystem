package de.ng.cloud.master.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import de.ng.cloud.master.module.loader.ModuleClassLoader;
import de.ng.cloud.master.module.loader.ModuleLoader;

public abstract class Module {
	
	private File dataFolder;
	private File configFile;
	
	private ModuleClassLoader moduleClassLoader;
	private ModuleLoader moduleLoader;
	
	private ModuleConfig moduleConfig;
	private Properties properties;
	
	public Module() { }
	
	public void onLoad() { }
	public void onEnable() { }
	public void onDisable() { }
	
	public String getName() {
		return moduleConfig != null ? moduleConfig.name : "unknown-plugin";
	}
	
	public File getDataFolder() {
		if(dataFolder == null)
			dataFolder = new File("modules/" + getName());
		return dataFolder;
	}
	
	public Properties getProperties() {
		getDataFolder().mkdir();
		
		if(configFile == null) {
			configFile = new File("modules/" + getName() + "/config.properties");
			
			if(!configFile.exists())
				try {
					configFile.createNewFile();
				} catch(IOException ex) {
					ex.printStackTrace();
				}
		}
		
		if(properties == null)
			try {
				properties = new Properties();
				properties.load(new FileInputStream(new File("modules/" + getName() + "/config.properties")));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		return properties;
	}
	
	public Module saveProperties() {
		try {
			properties.store(new FileOutputStream("modules/" + getName() + "/config.properties"), "");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return this;
	}
	
	public Properties loadProperties() {
		getDataFolder().mkdir();
		
		if(configFile == null) {
			configFile = new File("modules/" + getName() + "/config.properties");
			
			if(!configFile.exists())
				try {
					configFile.createNewFile();
				} catch(IOException ex) {
					ex.printStackTrace();
				}
		}
		
		try {
			properties = new Properties();
			properties.load(new FileInputStream(new File("modules/" + getName() + "/config.properties")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return properties;
	}
	
	
	
	public String getPluginName() {
		return getName();
	}
	
	public File getConfigFile() {
		return configFile;
	}
	
	public ModuleClassLoader getModuleClassLoader() {
		return moduleClassLoader;
	}
	
	public ModuleConfig getModuleConfig() {
		return moduleConfig;
	}
	
	public ModuleLoader getModuleLoader() {
		return moduleLoader;
	}
	
	public void setConfigFile(File configFile) {
		this.configFile = configFile;
	}
	
	public void setDataFolder(File dataFolder) {
		this.dataFolder = dataFolder;
	}
	
	public void setModuleClassLoader(ModuleClassLoader moduleClassLoader) {
		this.moduleClassLoader = moduleClassLoader;
	}
	
	public void setModuleConfig(ModuleConfig moduleConfig) {
		this.moduleConfig = moduleConfig;
	}
	
	public void setModuleLoader(ModuleLoader moduleLoader) {
		this.moduleLoader = moduleLoader;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}