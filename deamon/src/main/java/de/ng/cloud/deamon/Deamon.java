package de.ng.cloud.deamon;

import java.net.InetSocketAddress;

import de.ng.cloud.core.logger.CloudLogger;
import de.ng.cloud.core.network.CloudNetworkClient;
import de.ng.cloud.deamon.network.NetworkHandler;
import de.ng.cloud.deamon.network.NetworkPacketRegistry;

public class Deamon {
	
	public static void main(String[] args) {
		System.out.println("Starting deamon...");
		CloudLogger.initializing();
		
		CloudNetworkClient client = new CloudNetworkClient(new InetSocketAddress("localhost", 8080), NetworkPacketRegistry.INSTANCE, new NetworkHandler());
		client.tryConnect();
		
		System.out.println("Started deamon.");
		while(true);
	}
}