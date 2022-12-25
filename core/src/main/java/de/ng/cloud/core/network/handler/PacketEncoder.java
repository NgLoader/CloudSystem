package de.ng.cloud.core.network.handler;

import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.PacketRegistry;
import de.ng.cloud.core.network.packet.PacketRegistry.EnumPacketDirection;
import de.ng.cloud.core.network.packet.PacketSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
	
	private PacketRegistry registry;
	
	public PacketEncoder(PacketRegistry registry) {
		this.registry = registry;
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf output) throws Exception {
		int packetId = registry.getPacketId(EnumPacketDirection.OUT, packet);
		
		if(packetId == -1)
			throw new NullPointerException("Couldn't find id of packet " + packet.getClass().getSimpleName());
		PacketSerializer packetSerializer = new PacketSerializer(output);
		packetSerializer.writeVarInt(packetId);
		packet.write(packetSerializer);
	}
}