package de.ng.cloud.core.network.packet;

import java.io.IOException;

public interface Packet {
	
	public default void read(PacketSerializer packetSerializer) throws IOException { };
	public default void write(PacketSerializer packetSerializer) throws IOException { };
}