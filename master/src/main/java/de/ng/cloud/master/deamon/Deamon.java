package de.ng.cloud.master.deamon;

import de.ng.cloud.core.network.packet.packets.PacketDeamonPerformanceInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class Deamon {
	
	private int maxRam;
	private int usedRam;
	
	private PacketDeamonPerformanceInfo performanceInfo;
	
	private ChannelHandlerContext channelHandlerContext;
	
	public String address;
	public String username;
	
	public String token;
	
	public boolean checkAdress;
	
	public long ping;
	public long lastPacket;
	
	public Deamon(String address, String username, String token, boolean checkAdress) {
		this.address = address;
		this.username = username;
		this.token = token;
		this.checkAdress = checkAdress;
	}
	
	public boolean addRam(int ram) {
		if((usedRam + ram) > maxRam)
			return false;
		usedRam += ram;
		return true;
	}
	
	public void removeRam(int ram) {
		usedRam -= ram;
		if(usedRam < 0)
			usedRam = 0;
	}
	
	public PacketDeamonPerformanceInfo getPerformanceInfo() {
		return performanceInfo;
	}
	
	public ChannelHandlerContext getChannelHandlerContext() {
		return channelHandlerContext;
	}
	
	public Channel getChannel() {
		return channelHandlerContext.channel();
	}
	
	public void setPerformanceInfo(PacketDeamonPerformanceInfo performanceInfo) {
		this.performanceInfo = performanceInfo;
	}
	
	public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
		this.channelHandlerContext = channelHandlerContext;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Deamon) {
			Deamon deamon = (Deamon)obj;
			
			if(deamon.address.equals(this.address) &&
					deamon.token.equals(this.token) &&
					deamon.username.equals(this.username) &&
					deamon.checkAdress == this.checkAdress &&
					deamon.maxRam == this.maxRam &&
					deamon.usedRam == this.usedRam)
				return true;
		}
		return false;
	}
}