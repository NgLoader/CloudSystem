package de.ng.cloud.master;

import java.io.IOException;
import java.net.InetSocketAddress;

import de.ng.cloud.core.lang.LanguageManager;
import de.ng.cloud.core.logger.CloudLogger;
import de.ng.cloud.core.network.CloudNetworkServer;
import de.ng.cloud.master.config.CloudConfig;
import de.ng.cloud.master.deamon.DeamonManager;
import de.ng.cloud.master.module.ModuleManager;
import de.ng.cloud.master.network.NetworkHandler;
import de.ng.cloud.master.network.NetworkPacketRegistry;
import de.ng.cloud.master.template.TemplateManager;

public class Master {
	
	private static CloudNetworkServer cloudNetworkServer;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		CloudLogger.initializing();
		
//		System.setSecurityManager(new CloudSecurityManager());
		
		CloudConfig.CONFIG.load();
		LanguageManager.LANGUAGE_MANAGER.load(CloudConfig.CONFIG.getLanguage());
		TemplateManager.TEMPLATE_MANAGER.load();
		
		ModuleManager.MODULE_MANAGER.findModules();
		ModuleManager.MODULE_MANAGER.enableModules();
		
		cloudNetworkServer = new CloudNetworkServer(new InetSocketAddress("localhost", 8080), NetworkPacketRegistry.INSTANCE, new NetworkHandler());
		cloudNetworkServer.tryStart();
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			ModuleManager.MODULE_MANAGER.disableModules();
			
			if(cloudNetworkServer != null)
				cloudNetworkServer.close();
		}));
		
		System.out.println("Master started.");
		while (true);
	}
	
	public static ModuleManager getModuleManager() {
		return ModuleManager.MODULE_MANAGER;
	}
	
	public static TemplateManager geTemplateManager() {
		return TemplateManager.TEMPLATE_MANAGER;
	}
	
	public static DeamonManager getDeamonManager() {
		return DeamonManager.DEAMON_REGISTRY;
	}
	
	public static CloudConfig getCloudConfig() {
		return CloudConfig.CONFIG;
	}
}