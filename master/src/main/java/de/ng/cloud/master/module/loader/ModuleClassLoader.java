package de.ng.cloud.master.module.loader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import de.ng.cloud.master.module.Module;
import de.ng.cloud.master.module.ModuleConfig;

public class ModuleClassLoader implements ModuleLoader {
	
	private URLClassLoader urlClassLoader;
	private ModuleConfig moduleConfig;
	
	public ModuleClassLoader(ModuleConfig moduleConfig) throws MalformedURLException {
		this.moduleConfig = moduleConfig;
	}
	
	public Module loadModule() throws Exception {
		if(urlClassLoader != null)
			urlClassLoader.close();
		urlClassLoader = new URLClassLoader(new URL[] { moduleConfig.file.toURI().toURL() });
		
		Class<?> bootstrap = urlClassLoader.loadClass(this.moduleConfig.main);
		Module module = (Module) bootstrap.getDeclaredConstructor().newInstance();
		
		module.setModuleConfig(moduleConfig);
		module.setModuleClassLoader(this);
		module.onLoad();
		
		return module;
	}
	
	public void unloadModule() {
		try {
			urlClassLoader.close();
			urlClassLoader = null;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}