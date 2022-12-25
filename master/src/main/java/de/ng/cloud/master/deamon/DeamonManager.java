package de.ng.cloud.master.deamon;

import java.util.LinkedList;
import java.util.List;

import io.netty.channel.Channel;

public class DeamonManager {
	
	public static final DeamonManager DEAMON_REGISTRY = new DeamonManager();
	
	private static final List<Deamon> DEAMONS = new LinkedList<>();
	
	public static boolean addDeamon(Deamon deamon) {
		for(Deamon existingDeamon : DEAMONS)
			if(existingDeamon.equals(deamon))
				return false;
		return DEAMONS.add(deamon);
	}
	
	public static boolean removeDeamon(Deamon deamon) {
		return DEAMONS.remove(deamon);
	}
	
	public static Deamon findDeamon(String username) {
		for(Deamon deamon : DEAMONS)
			if(deamon.username.equals(username))
				return deamon;
		return null;
	}
	
	public static Deamon findDeamon(Channel channel) {
		for(Deamon deamon : DEAMONS)
			if(deamon.getChannelHandlerContext() != null && deamon.getChannel().equals(channel))
				return deamon;
		return null;
	}
}