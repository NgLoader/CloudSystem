package de.ng.cloud.addon.example;

import de.ng.cloud.master.module.Module;

public class Example extends Module {
	
	public static Example instance;
	
	public Example() {
		instance = this;
	}
	
	@Override
	public void onLoad() {
		System.out.println("Good moring");
	}
	
	@Override
	public void onEnable() {
		System.out.println("Hallo world");
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("Good night");
	}
}