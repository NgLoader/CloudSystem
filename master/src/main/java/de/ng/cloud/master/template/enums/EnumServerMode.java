package de.ng.cloud.master.template.enums;

public enum EnumServerMode {
	
	STATIC,
	STATIC_LOBBY,
	LOBBY,
	DYNAMIC;
	
	private String[] aliases;
	
	private EnumServerMode(String... aliases) {
		this.aliases = aliases;
	}
	
	public static EnumServerMode findEnumByName(String name) {
		for(EnumServerMode serverMode : values())
			if(serverMode.name().equalsIgnoreCase(name))
				return serverMode;
			else
				for(String alias : serverMode.aliases)
					if(alias.equalsIgnoreCase(name))
						return serverMode;
		return null;
	}
}