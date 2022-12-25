package de.ng.cloud.core.lang;

public class I18n {
	
	public static Language language;
	
	public static String format(String translateKey, Object... parameters) {
		return language != null ? language.getProperty(translateKey) : translateKey;
	}
	
	public static String formatLocale(String translateKey, String locale, Object... parameters) {
		Language lang = LanguageManager.LANGUAGE_MANAGER.getLanguageByLocale(locale);
		return language != null && lang != null ? lang.getProperty(translateKey) : translateKey;
	}
}