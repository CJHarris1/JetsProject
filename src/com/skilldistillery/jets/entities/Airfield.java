package com.skilldistillery.jets.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AirField {
	private List<Jet> fleet;

	public AirField() {
		fleet = new ArrayList<>();
	}
	
	public void listFleet() {
		// calls the toString for each jet currently in the airField
		fleet = getFleet();
		for (Jet jet : fleet) {
			System.out.println(jet);
		}
	}

	public void flyAll() {
		// iterates through each jet and calls its fly method
		fleet = getFleet();
		for (Jet jet : fleet) {
			jet.fly();
		}
		System.out.println();
	}

	public String fastestJet() {
		double speed = 0; // placeholder to compare each speed to the next
		String fastest = null; // placeholder toString for the fastest
		double mach = 0; // placeholder for mach conversion
		String fastestAndMach = null; // to be returned with fastest jet and mach conversion

		// checks each jet in the fleet and compares speeds
		fleet = getFleet();
		for (int i = 0; i < fleet.size(); i++) {
			double currentSpeed = fleet.get(i).getSpeed(); // speed of jet in current index
			if (currentSpeed > speed) {
				speed = currentSpeed;
				fastest = fleet.get(i).toString(); // current fastest toString
				mach = fleet.get(i).getSpeedInMach(); // current fastest mach conversion
				fastestAndMach = fastest + "Which converts to mach " + mach + "!";
			}
		}
		return fastestAndMach;
	}

	public String longestRange() {
		double range = 0; // placeholder to compare each range to the next
		String longestRange = null; // placeholder toString for the longest range

		// checks each jet in the fleet and compares range
		fleet = getFleet();
		for (int i = 0; i < fleet.size(); i++) {
			double currentRange = fleet.get(i).getRange(); // range of jet in current index
			if (currentRange > range) {
				range = currentRange;
				longestRange = fleet.get(i).toString(); // current longestRange toString
			}
		}
		return longestRange;
	}

	public void stealthMode() {
//		checks each jet in the fleet if it is a stealthPlane and then calls the interface method
		String name = null;
		fleet = getFleet();
		for (int i = 0; i < fleet.size(); i++) {
			if (fleet.get(i) instanceof StealthPlane) {
				name = fleet.get(i).getModel();
				StealthPlane stealthPlane = (StealthPlane) fleet.get(i); // Downcast in order to call the method for
																			// stealthPlane
				System.out.print(name + ": ");
				stealthPlane.offTheRadar();
			}
		}
	}

	public void dogFight() {
//		checks each jet in the fleet if it is a fighter jet and then calls the interface method
		String name = null;
		fleet = getFleet();
		for (int i = 0; i < fleet.size(); i++) {
			if (fleet.get(i) instanceof FighterJet) {
				name = fleet.get(i).getModel();
				FighterJet fighterJet = (FighterJet) fleet.get(i); // Downcast in order to call the method for fighter
																	// jet
				System.out.print(name + ": ");
				fighterJet.fight();
			}
		}
	}

	public void addJet(Scanner scanner) {
		boolean wantsToAddJet = true;
		String choseJet = null;

		while (wantsToAddJet) {
			boolean validJet = false;
			while (!validJet) {
				// loops to get valid choice of jet type and assigns choice to chose jet
				System.out.println("Which type of Jet is this? \n" + "1) Fighter Jet \n" + "2) Stealth Plane \n"
						+ "3) Passenger Jet \n");
				String whichJet = scanner.nextLine();
				switch (whichJet) {
				case "1":
					choseJet = "fighter";
					validJet = true;
					break;
				case "2":
					choseJet = "stealth";
					validJet = true;
					break;
				case "3":
					choseJet = "passenger";
					validJet = true;
					break;
				default:
					System.out.println("Invalid choice, try again.");
				}
			}
			// retrieves all constructor fields to create new instance of a jet
			String model = getModelNewJet(scanner);
			double speed = getSpeedNewJet(scanner);
			int range = getRangeNewJet(scanner);
			long price = getPriceNewJet(scanner);

			// instantiates a jet depending on which type
			switch (choseJet) {
			case "fighter":
				Jet fighter = new FighterJet(model, speed, range, price);
				fleet.add(fighter);
				wantsToAddJet = false;
				break;
			case "stealth":
				Jet stealth = new StealthPlane(model, speed, range, price);
				fleet.add(stealth);
				wantsToAddJet = false;
				break;
			case "passenger":
				Jet passenger = new PassengerJet(model, speed, range, price);
				fleet.add(passenger);
				wantsToAddJet = false;
				break;
			}
		}
	}

	public String getModelNewJet(Scanner scanner) {
		String model = null;
		boolean validModel = false;

		while (!validModel) {
			System.out.print("What is the model of the Jet? ");
			try {
				model = scanner.nextLine();
				validModel = true;
			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();
			}
		}
		return model;
	}

	public double getSpeedNewJet(Scanner scanner) {
		double speed = 0;
		boolean validSpeed = false;

		while (!validSpeed) {
			System.out.print("What is the speed of the Jet in mph? ");
			try {
				speed = scanner.nextDouble();
				scanner.nextLine(); // flush
				validSpeed = true;

			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();
			}
		}
		return speed;
	}

	public int getRangeNewJet(Scanner scanner) {
		int range = 0;
		boolean validRange = false;

		while (!validRange) {
			System.out.print("What is the range of this Jet in miles? ");
			try {
				range = scanner.nextInt();
				scanner.nextLine(); // flush
				validRange = true;

			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();
			}
		}
		return range;
	}

	public long getPriceNewJet(Scanner scanner) {
		long price = 0;
		boolean validPrice = false;

		while (!validPrice) {
			System.out.print("What is the dollar ammount of this Jet? ");
			try {
				price = scanner.nextLong();
				scanner.nextLine(); // flush
				System.out.println();
				validPrice = true;

			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();
			}
		}
		return price;
	}

	public void addAnotherJet(Scanner scanner) {
		// takes user input and either adds another jet, or goes back to main menu
		boolean addAnother = true;
		while (addAnother) {
			System.out.println("Jet has been added, would you like to add another? \n" + "1) Yes, add another Jet \n"
					+ "2) No, take me back to the main menu \n");
			String addAnotherJet = scanner.nextLine();

			switch (addAnotherJet) {
			case "1":
				addJet(scanner);
				break;
			case "2":
				addAnother = false;
				break;
			default:
				System.out.println("Invalid choice, try again.");
			}
		}
	}

	public void removeJet(Scanner scanner) {
		int whichJetToRemove = -1;
		boolean removedJet = false;

		while (!removedJet) {
			System.out.println("Which Jet would you like to remove? ");
			for (int i = 0; i < fleet.size(); i++) {
				System.out.println("" + (i + 1) + ") " + fleet.get(i)); // prints out each jet in the
																						// fleet along with a number
																						// index for user to choose
			}
			try {
				whichJetToRemove = scanner.nextInt();
				scanner.nextLine();
				fleet.remove(whichJetToRemove - 1); // removes jet at index user chose
				removedJet = true;

			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
			}
		}
	}

	public void removeAnotherJet(Scanner scanner) {
		// takes user input and either removes another jet, or goes back to main menu
		boolean removeAnother = true;
		while (removeAnother) {
			System.out.println("Jet has been removed, would you like to remove another? \n"
					+ "1) Yes, remove another Jet \n" + "2) No, take me back to the main menu \n");
			String removeAnotherJet = scanner.nextLine();

			switch (removeAnotherJet) {
			case "1":
				removeJet(scanner);
				break;
			case "2":
				removeAnother = false;
				break;
			default:
				System.out.println("Invalid choice, try again.");
			}
		}
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
