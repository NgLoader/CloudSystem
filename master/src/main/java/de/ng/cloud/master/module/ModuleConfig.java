package de.ng.cloud.master.module;

import java.io.File;

public class ModuleConfig {
	
	public File file;
	
	public String name;
	public String version;
	public String author;
	public String main;

	public ModuleConfig(File file, String name, String version, String author, String main) {
		this.file = file;
		this.name = name;
		this.version = version;
		this.author = author;
		this.main = main;
	}
}