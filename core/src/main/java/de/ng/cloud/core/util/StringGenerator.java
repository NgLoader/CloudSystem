package de.ng.cloud.core.util;

public enum StringGenerator {
	
	ALPHA("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
	NUMERIC("1234567890"),
	SPECIAL(";:#'+*^°´?=)(/&%$§\\\"!\\\\-.,<>|"),
	
	ALPHANUMERIC("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"),
	ALPHANUMERICSPECIAL("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890;:#'+*^°´?=)(/&%$§\\\"!\\\\-.,<>|");
	
	private final String characters;
	private final int charactersLength;
	
	private StringGenerator(String characters) {
		this.characters = characters;
		this.charactersLength = characters.length();
	}
	
	public String generateString(int length) {
		StringBuffer buffer = new StringBuffer();
		
		for(int i = 0; i < length; i++)
			buffer.append(characters.charAt((int)Math.random() * charactersLength));
		
		return buffer.toString();
	}
	
	public String getCharacters() {
		return characters;
	}
	
	public int getCharactersLength() {
		return charactersLength;
	}
}