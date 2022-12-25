package de.ng.cloud.master.template;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.ng.cloud.core.util.FileUtil;
import de.ng.cloud.master.template.enums.EnumBackend;
import de.ng.cloud.master.template.enums.EnumServerMode;
import de.ng.cloud.master.template.enums.EnumTemplateStartMode;

public class Template {
	
	private File folder;
	
	private String name;
	private String[] deamons;
	private EnumServerMode serverMode;
	private EnumBackend backend;
	private String backendUrl;
	private int memory;
	private int joinPower;
	private int maxOnlineServers;
	private int minOnlineServers;
	private int percentForNewServer;
	private EnumTemplateStartMode templateStartMode;
	private boolean maintenance;
	private int maintenanceJoinPower;
	
	private List<SubTemplate> subTemplates;
	
	public Template(File folder) {
		this.folder = folder;
		this.subTemplates = new ArrayList<>();
	}
	
	public boolean load() {
		if(!folder.exists())
			folder.mkdir();
		
		checkExist(new File(folder.getPath() + "/settings.properties"), folder.getName());
		
		try {
			Properties properties = new Properties();
			FileInputStream inputStream = new FileInputStream(folder.getPath() + "/settings.properties");
			properties.load(inputStream);
			inputStream.close();
			
			this.name = getPropertiesValue(properties, "name");
			this.deamons = getPropertiesValue(properties, "deamons").split(",");
			this.serverMode = EnumServerMode.findEnumByName(getPropertiesValue(properties, "servermode"));
			this.backend = EnumBackend.findEnumByName(getPropertiesValue(properties, "backend"));
			this.backendUrl = getPropertiesValue(properties, "backendUrl");
			this.memory = Integer.valueOf(getPropertiesValue(properties, "memory"));
			this.joinPower = Integer.valueOf(getPropertiesValue(properties, "joinpower"));
			this.maxOnlineServers = Integer.valueOf(getPropertiesValue(properties, "maxOnlineServers"));
			this.minOnlineServers = Integer.valueOf(getPropertiesValue(properties, "minOnlineServers"));
			this.percentForNewServer = Integer.valueOf(getPropertiesValue(properties, "percentForNewServer").replaceAll("%", ""));
			this.templateStartMode = EnumTemplateStartMode.findEnumByName(getPropertiesValue(properties, "templateStartMode"));
			this.maintenance = Boolean.valueOf(getPropertiesValue(properties, "maintenance"));
			this.maintenanceJoinPower = Integer.valueOf(getPropertiesValue(properties, "maintenanceJoinPower"));
			
		} catch (Exception ex) {
			System.out.println("Failed to load file \"" + folder.getPath() + "/settings.properties\".");
			ex.printStackTrace();
			return false;
		}

		for (File file : new File(folder.getPath() + "/templates").listFiles())
			if (file.isDirectory()) {
				SubTemplate subTemplate = new SubTemplate(file);
				if (subTemplate.loadSubTemplate())
					subTemplates.add(subTemplate);
			}
		return true;
	}
	
	private void checkExist(File file, String template) {
		if(!file.exists())
			try {
				System.out.println("Template settings for \"" + template + "\" not found. generating...");
				FileUtil.copyLocalResourceFileToFolder("cloud/settings/template.properties", "settings.properties", folder);
			} catch (Exception ex) {
				System.out.println("Failed to create file \"" + file.getPath() + ".");
				ex.printStackTrace();
			}
	}
	
	private String getPropertiesValue(Properties properties, String key) {
		if(properties.containsKey(key))
			return properties.getProperty(key);
		throw new NullPointerException("Properties key not found: key=\"" + key + "\"");
	}
	
	public File getFolder() {
		return folder;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getDeamons() {
		return deamons;
	}
	
	public EnumBackend getBackend() {
		return backend;
	}
	
	public String getBackendUrl() {
		return backendUrl;
	}
	
	public int getJoinPower() {
		return joinPower;
	}
	
	public int getMaintenanceJoinPower() {
		return maintenanceJoinPower;
	}
	
	public int getMaxOnlineServers() {
		return maxOnlineServers;
	}
	
	public int getMemory() {
		return memory;
	}
	
	public int getMinOnlineServers() {
		return minOnlineServers;
	}
	
	public int getPercentForNewServer() {
		return percentForNewServer;
	}
	
	public EnumServerMode getServerMode() {
		return serverMode;
	}
	
	public EnumTemplateStartMode getTemplateStartMode() {
		return templateStartMode;
	}
	
	public boolean isMaintenance() {
		return maintenance;
	}
	
	public List<SubTemplate> getSubTemplates() {
		return subTemplates;
	}
}