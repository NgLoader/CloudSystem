package de.ng.cloud.master.module;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import de.ng.cloud.master.module.exception.ModuleLoadException;
import io.netty.util.CharsetUtil;

public class ModuleFinder {

	public List<ModuleConfig> findModules(File direction) {
		List<ModuleConfig> moduleConfigs = new ArrayList<>();

		for (File file : direction.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.exists() && pathname.getName().endsWith(".jar");
			}
		}))
			try (JarFile jarFile = new JarFile(file)) {
				JarEntry jarEntry = jarFile.getJarEntry("module.properties");
				
				if (jarEntry == null)
					throw new ModuleLoadException("Cannot find \"module.properties\" file in " + file.getName());

				try (InputStream inputStream = jarFile.getInputStream(jarEntry);
						InputStreamReader inputStreamReader = new InputStreamReader(inputStream, CharsetUtil.UTF_8)) {
					Properties properties = new Properties();
					properties.load(inputStreamReader);
					
					moduleConfigs.add(new ModuleConfig(file, properties.getProperty("name", "Unknown"), properties.getProperty("version", "Unknown"), properties.getProperty("author", "Unknown"), properties.getProperty("main", "")));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		return moduleConfigs;
	}
}