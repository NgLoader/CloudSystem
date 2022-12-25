package de.ng.cloud.master.template.enums;

public enum EnumBackend {

	MASTER,
	LOCAL,
	URL;

	private String[] aliases;

	private EnumBackend(String... aliases) {
		this.aliases = aliases;
	}

	public static EnumBackend findEnumByName(String name) {
		for (EnumBackend serverMode : values())
			if (serverMode.name().equalsIgnoreCase(name))
				return serverMode;
			else
				for (String alias : serverMode.aliases)
					if (alias.equalsIgnoreCase(name))
						return serverMode;
		return null;
	}
}