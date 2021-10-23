package com.skilldistillery.jets.entities;

import java.util.Objects;

public abstract class Jet {
	private String model;
	private double speed;
	private int range;
	private long price;
	
	public Jet(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	public void fly() {
		//trimmed the double to 2 decimal places for cleaner output
		int timeInAir = (int)(this.range / this.speed * 100.0);
		double timeInAirDouble = timeInAir / 100.0;
		
		System.out.println("The " + this.model + " can fly for " + timeInAirDouble + " hours at max speed.");
	}
	
	public double getSpeedInMach() {
		//trimmed the double to 2 decimal places for cleaner output
		int speedInMach = (int) (this.speed / 767 * 100);
		double speedInMachDouble = speedInMach / 100.0; //conversion from mph to speed of sound
		 return speedInMachDouble;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Jet model: ").append(model).append("\n speed in mph: ").append(speed).append("\n range in miles: ").append(range)
				.append("\n price $").append(price).append("\n\n");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(model, price, range, speed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jet other = (Jet) obj;
		return Objects.equals(model, other.model) && price == other.price && range == other.range
				&& Double.doubleToLongBits(speed) == Double.doubleToLongBits(other.speed);
	}
	
	
}
