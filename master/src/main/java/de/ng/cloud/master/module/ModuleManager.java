package de.ng.cloud.master.module;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.ng.cloud.master.module.loader.ModuleClassLoader;
import de.ng.cloud.master.module.loader.ModuleLoader;

public class ModuleManager {
	
	public static final ModuleManager MODULE_MANAGER = new ModuleManager();
	
	private ModuleFinder moduleFinder = new ModuleFinder();
	private File directory = new File("Cloud/modules");
	
	private final Collection<Module> modules = new ConcurrentLinkedQueue<>();
	private final Collection<String> disabledModuleList = new ArrayList<>();
	
	public ModuleManager() {
		directory.mkdir();
	}
	
	public Collection<ModuleConfig> findModules() {
		return findModules(directory);
	}
	
	public Collection<ModuleConfig> findModules(File directory) {
		return moduleFinder.findModules(directory);
	}
	
	public ModuleManager loadModules() {
		return loadModules(directory);
	}
	
	public ModuleManager loadModules(File directory) {
		Collection<ModuleConfig> configs = findModules(directory);
		
		for (ModuleConfig config : configs)
			try {
				if (!disabledModuleList.contains(config.name)) {
					System.out.println("Loading module \"" + config.name + "\" version: " + config.version + ".");

					ModuleLoader moduleLoader = new ModuleClassLoader(config);
					Module module = moduleLoader.loadModule();

					module.setModuleLoader(moduleLoader);
					module.setDataFolder(directory);
					this.modules.add(module);
				}
			} catch (Exception ex) {
				System.out.println("Failed to load module \"" + config.name + "\"");
				ex.printStackTrace();
			}
		return this;
	}
	
	public ModuleManager enableModules() {
		for(Module module : modules) {
			System.out.println("Enabling module \"" + module.getModuleConfig().name + "\" version: " + module.getModuleConfig().version + ".");
			module.onEnable();
		}
		return this;
	}
	
	public ModuleManager disableModules() {
		for(Module module : modules) {
			System.out.println("Enabling module \"" + module.getModuleConfig().name + "\" version: " + module.getModuleConfig().version + ".");
			module.onDisable();
		}
		return this;
	}
	
	public ModuleManager enableModule(Module module) {
		System.out.println("Enabling module \"" + module.getModuleConfig().name + "\" version: " + module.getModuleConfig().version + ".");
		module.onEnable();
		return this;
	}
	
	public ModuleManager disableModule(Module module) {
		System.out.println("Disabling module \"" + module.getModuleConfig().name + "\" version: " + module.getModuleConfig().version + ".");
		module.onDisable();
		module.getModuleClassLoader().unloadModule();
		return this;
	}
	
	public Module findModule(String name) {
		for(Module module : modules)
			if(module.getName().equalsIgnoreCase(name))
				return module;
		return null;
	}
	
	public void shutdown() {
		for(Module module : modules) {
			module.onDisable();
			module.getModuleClassLoader().unloadModule();
		}
	}
	
	public Collection<String> getDisabledModuleList() {
		return disabledModuleList;
	}
	
	public Collection<Module> getModules() {
		return modules;
	}
	
	public ModuleFinder getModuleFinder() {
		return moduleFinder;
	}
}