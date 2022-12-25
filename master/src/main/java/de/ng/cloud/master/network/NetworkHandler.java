package de.ng.cloud.master.network;

import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.packets.PacketAlive;
import de.ng.cloud.core.network.packet.packets.PacketDeamonPerformanceInfo;
import de.ng.cloud.core.network.packet.packets.PacketInitializing;
import de.ng.cloud.master.deamon.Deamon;
import de.ng.cloud.master.deamon.DeamonManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NetworkHandler extends SimpleChannelInboundHandler<Packet> {
	
	private ChannelGroup registeredChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
		if(registeredChannels.contains(ctx.channel())) {
			Deamon deamon = DeamonManager.findDeamon(ctx.channel());
			deamon.lastPacket = System.currentTimeMillis();
			
			if(msg instanceof PacketAlive) {
				deamon.ping = System.currentTimeMillis() - ((PacketAlive)msg).time;
				return;
			} else if(msg instanceof PacketDeamonPerformanceInfo) {
				deamon.setPerformanceInfo((PacketDeamonPerformanceInfo)msg);
				return;
			}
		} else if(msg instanceof PacketInitializing) {
			PacketInitializing packet = (PacketInitializing) msg;
			
			Deamon deamon = DeamonManager.findDeamon(packet.deamonName);
			
			if(deamon == null) {
				ctx.close();
				return;
			}
			deamon.setChannelHandlerContext(ctx);
			
			System.out.println("Registered: " + packet.deamonName);
		} else
			ctx.close();
	}
}