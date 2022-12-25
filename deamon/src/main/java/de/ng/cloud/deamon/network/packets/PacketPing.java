package de.ng.cloud.deamon.network.packets;

import java.io.IOException;

import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.PacketSerializer;

public class PacketPing implements Packet {
	
	public long time;
	
	public PacketPing() { }
	
	public PacketPing(long time) {
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