package de.ng.cloud.core.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class Language {
	
	private Locale locale;
	private File file;
	private Properties properties;
	
	public Language(Locale locale, File file) {
		this.locale = locale;
		this.file = file;
	}
	
	public boolean load() {
		try {
			FileInputStream inputStream = new FileInputStream(file);
			boolean success = load(inputStream);
			inputStream.close();
			return success;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean load(InputStream inputStream) {
		try {
			if(properties != null)
				properties.clear();
			else
				properties = new Properties();
			properties.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public File getFile() {
		return file;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key, "Language key: \"" + key + "\" not found.");
	}
}