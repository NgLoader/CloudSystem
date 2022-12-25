package de.ng.cloud.core.network.packet.packets;

import java.io.IOException;

import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.PacketSerializer;

public class PacketDeamonPerformanceInfo implements Packet {
	
	public int realRam;
	public double usedMemory, freeMemory, totalMemory;
	public double cpuUsage, averageCPU;
	
	public PacketDeamonPerformanceInfo() { }
	
	public PacketDeamonPerformanceInfo(int realRam, int usedMemory, int freeMemory, int totalMemory, double cpuUsage, double averageCPU) {
		this.realRam = realRam;
		this.usedMemory = usedMemory;
		this.freeMemory = freeMemory;
		this.totalMemory = totalMemory;
		this.cpuUsage = cpuUsage;
		this.averageCPU = averageCPU;
	}
	
	@Override
	public void read(PacketSerializer packetSerializer) throws IOException {
		this.realRam = packetSerializer.readInt();
		this.usedMemory = packetSerializer.readDouble();
		this.freeMemory = packetSerializer.readDouble();
		this.totalMemory = packetSerializer.readDouble();
		this.cpuUsage = packetSerializer.readDouble();
		this.averageCPU = packetSerializer.readDouble();
	}
	
	@Override
	public void write(PacketSerializer packetSerializer) throws IOException {
		packetSerializer.writeInt(realRam);
		packetSerializer.writeDouble(usedMemory);
		packetSerializer.writeDouble(freeMemory);
		packetSerializer.writeDouble(totalMemory);
		packetSerializer.writeDouble(cpuUsage);
		packetSerializer.writeDouble(averageCPU);
	}
}