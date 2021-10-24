package com.skilldistillery.jets.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AirField {
	private List<Jet> fleet;

	public AirField() {
		fleet = new ArrayList<>();
	}

	public List<Jet> getFleet() {
		return fleet;
	}

	public void setFleet(List<Jet> fleet) {
		this.fleet = fleet;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Harris AirField currently has these jets in the fleet: \n").append(fleet);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(fleet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirField other = (AirField) obj;
		return Objects.equals(fleet, other.fleet);
	}

}
