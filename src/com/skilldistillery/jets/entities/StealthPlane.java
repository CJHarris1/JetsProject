package com.skilldistillery.jets.entities;

public class StealthPlane extends Jet implements StealthMode{

	public StealthPlane(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	@Override
	public void offTheRadar() {
		System.out.println("Going dark!");
	}
	
	

}
