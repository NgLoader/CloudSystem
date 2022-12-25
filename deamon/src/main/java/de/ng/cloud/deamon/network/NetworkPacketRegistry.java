package de.ng.cloud.deamon.network;

import de.ng.cloud.core.network.packet.PacketRegistry;
import de.ng.cloud.core.network.packet.packets.PacketAlive;
import de.ng.cloud.core.network.packet.packets.PacketDeamonPerformanceInfo;
import de.ng.cloud.core.network.packet.packets.PacketInitializing;

public class NetworkPacketRegistry extends PacketRegistry {
	
	public static final NetworkPacketRegistry INSTANCE = new NetworkPacketRegistry();
	
	public NetworkPacketRegistry() {
		addPacket(EnumPacketDirection.BOTH, PacketAlive.class);
		addPacket(EnumPacketDirection.BOTH, PacketDeamonPerformanceInfo.class);
		addPacket(EnumPacketDirection.BOTH, PacketInitializing.class);
	}
}