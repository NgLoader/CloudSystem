package de.ng.cloud.master.module.loader;

import de.ng.cloud.master.module.Module;

public interface ModuleLoader {
	
	Module loadModule() throws Exception;
	void unloadModule() throws Exception;
}