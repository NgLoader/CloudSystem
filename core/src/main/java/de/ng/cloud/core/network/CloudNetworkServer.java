package de.ng.cloud.core.network;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import de.ng.cloud.core.network.handler.PacketDecoder;
import de.ng.cloud.core.network.handler.PacketEncoder;
import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.PacketRegistry;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;

public class CloudNetworkServer extends NetworkServer {
	
	/* REMOVED code from a friend */
}