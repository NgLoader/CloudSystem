package de.ng.cloud.core.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.ng.cloud.core.util.FileUtil;

public class LanguageManager {
	
	public static final LanguageManager LANGUAGE_MANAGER = new LanguageManager(new File("./Cloud/lang"));
	
	private File folder;
	private Language currentlyLanguage;
	private List<Language> languages = new ArrayList<>();
	
	public LanguageManager(File folder) {
		this.folder = folder;
		
		//Load default
		try {
			Language language = new Language(new Locale("en_US"), new File("cloud/lang"));
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("cloud/lang/en_US.properties");
			if(language.load(inputStream))
				setCurrentlyLanguage(language);
			inputStream.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void load(String defaultLocale) {
		if(!folder.exists()) {
			try {
				System.out.println("Coudn't find lang folder. generating...");
				FileUtil.copyLocalResourceFolderToFolder("cloud/lang", new File("./Cloud/lang"));
			} catch(IOException ex) {
				System.out.println("Failed to create lang folder.");
				ex.printStackTrace();
			}
		}
		
		for(File file : folder.listFiles())
			if(!file.isDirectory()) {
				Language language = new Language(new Locale(file.getName().replace(".properties", "")), file);
				if(language.load()) {
					languages.add(language);
					
					if(defaultLocale.equalsIgnoreCase(language.getLocale().getLanguage()))
						setCurrentlyLanguage(language);
				}
			}
	}
	
	public Language getLanguageByLocale(String locale) {
		for(Language language : languages)
			if(language.getLocale().getLanguage().equalsIgnoreCase(locale) || language.getLocale().toLanguageTag().equalsIgnoreCase(locale))
				return language;
		return null;
	}
	
	public void setCurrentlyLanguage(Language language) {
		this.currentlyLanguage = language;
		I18n.language = language;
	}
	
	public Language getCurrentlyLanguage() {
		return currentlyLanguage;
	}
}