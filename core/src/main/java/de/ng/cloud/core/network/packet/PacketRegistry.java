package de.ng.cloud.core.network.packet;

import java.util.ArrayList;
import java.util.List;

public abstract class PacketRegistry {
	
	private final List<Class<? extends Packet>> inPackets = new ArrayList<>();
	private final List<Class<? extends Packet>> outPackets = new ArrayList<>();
	
	public final List<Class<? extends Packet>> getPacketList(EnumPacketDirection direction) {
		if(direction == EnumPacketDirection.BOTH)
			return new ArrayList<>();
		return direction == EnumPacketDirection.IN ? this.inPackets : this.outPackets;
	}
	
	public final int getPacketId(EnumPacketDirection direction, Packet packet) {
		return getPacketList(direction).indexOf(packet.getClass());
	}
	
	public final Packet getPacketById(EnumPacketDirection direction, int packetId) throws InstantiationException, IllegalAccessException {
		return getPacketList(direction).get(packetId).newInstance();
	}
	
	protected void addPacket(EnumPacketDirection direction, Class<? extends Packet> packetClass) {
		switch (direction) {
		case IN:
			inPackets.add(packetClass);
			break;

		case OUT:
			outPackets.add(packetClass);
			break;

		case BOTH:
			inPackets.add(packetClass);
			outPackets.add(packetClass);
			break;
		default:
			break;
		}
	}
	
	public enum EnumPacketDirection {
		IN, OUT, BOTH
	}
}