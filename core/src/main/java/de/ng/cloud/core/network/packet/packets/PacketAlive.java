package de.ng.cloud.core.network.packet.packets;

import java.io.IOException;

import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.PacketSerializer;

public class PacketAlive implements Packet {
	
	public long time;
	
	public PacketAlive() { }
	
	public PacketAlive(long time) {
		this.time = time;
	}
	
	@Override
	public void read(PacketSerializer packetSerializer) throws IOException {
		this.time = packetSerializer.readLong();
	}
	
	@Override
	public void write(PacketSerializer packetSerializer) throws IOException {
		packetSerializer.writeLong(time);
	}
}