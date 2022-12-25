package de.ng.cloud.core.network.handler;

import java.util.List;

import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.PacketRegistry;
import de.ng.cloud.core.network.packet.PacketRegistry.EnumPacketDirection;
import de.ng.cloud.core.network.packet.PacketSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder {
	
	private PacketRegistry registry;
	
	public PacketDecoder(PacketRegistry registry) {
		this.registry = registry;
	}
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> output) throws Exception {
		PacketSerializer packetSerializer = new PacketSerializer(byteBuf);
		int packetId = packetSerializer.readVarInt();
		Packet packet = registry.getPacketById(EnumPacketDirection.IN, packetId);
		if(packet == null)
			throw new NullPointerException("Couldn't find packet by id " + packetId);
		packet.read(packetSerializer);
		output.add(packet);
	}
}