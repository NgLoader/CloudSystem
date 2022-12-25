package de.ng.cloud.master.template.enums;

public enum EnumTemplateStartMode {

	ROUNDROBIN,
	RANDOM;
	
	private String[] aliases;

	private EnumTemplateStartMode(String... aliases) {
		this.aliases = aliases;
	}

	public static EnumTemplateStartMode findEnumByName(String name) {
		for (EnumTemplateStartMode serverMode : values())
			if (serverMode.name().equalsIgnoreCase(name))
				return serverMode;
			else
				for (String alias : serverMode.aliases)
					if (alias.equalsIgnoreCase(name))
						return serverMode;
		return null;
	}
}