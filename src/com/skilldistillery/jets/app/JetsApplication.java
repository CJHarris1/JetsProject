package com.skilldistillery.jets.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;
import com.skilldistillery.jets.entities.FighterJet;
import com.skilldistillery.jets.entities.Jet;
import com.skilldistillery.jets.entities.PassengerJet;
import com.skilldistillery.jets.entities.StealthPlane;


public class JetsApplication {
	private AirField airField = new AirField();
	private Scanner scanner = new Scanner(System.in);
	
	
	public JetsApplication() {}
	

	public static void main(String[] args) {
		JetsApplication jetApp = new JetsApplication();
		
		jetApp.launch();
		jetApp.displayUserMenu();
		jetApp.cleanUp();
	}

	private void launch() {
		//reads through the jets file and separates out each line and enters into fields
		try ( BufferedReader bufIn = new BufferedReader(new FileReader("jets.txt")) ) {
			  String line;
			  Jet j;
			  List<Jet> jets = new ArrayList<>();    //List to add each jet from the file
			  while ((line = bufIn.readLine()) != null) {
				  String[] jetRecord = line.split(",");
				  String type  = jetRecord[0];
				  String model = jetRecord[1];
	              double speed = Double.valueOf(jetRecord[2]);
	              int range = Integer.parseInt(jetRecord[3]);
	              long price = Long.valueOf(jetRecord[4]);
	              
	              //check which type of jet is being instantiated
	              if(type.equals("FighterJet")){
	            	  j = new FighterJet(model, speed, range, price);
	              }
	              else if(type.equals("StealthPlane")) {
	            	  j = new StealthPlane(model, speed, range, price);
	              }
	              else {
	            	  j = new PassengerJet(model, speed, range, price);
	              }
	              jets.add(j);
			  }
			  airField.setFleet(jets);    //sets the fleet field to the Collection of Jets from file
			}
		
			catch (IOException e) {
			  System.err.println(e);
			}
	}
	
	private void displayUserMenu() {
		boolean wantsToQuit = false;
		while(!wantsToQuit) {
			System.out.println("Which option would you like? \n"
							 + "1) List fleet \n"
							 + "2) Fly all jets \n"
							 + "3) View fastest jet \n"
							 + "4) View jet with longest range \n"
							 + "5) Enter Stealth mode \n"
							 + "6) Dogfight! \n"
							 + "7) Add a jet to Fleet \n"
							 + "8) Remove a jet from Fleet \n"
							 + "9) Quit");
	
		String choice = scanner.nextLine();
			switch(choice) {
			case "1":
				listFleet();
				break;
			case "2":
				flyAll();
				break;
			case "3":
				System.out.println("The fastest jet information: \n" + fastestJet() + "\n");
				break;
			case "4":
				System.out.println("The jet with the furthest range information: \n" + longestRange()); ;
				break;
			case "5":
				stealthMode();
				System.out.println(); // adding a space between the output and the next menu loop
				break;
			case "6":
				dogFight();
				System.out.println(); // adding a space between the output and the next menu loop
				break;
			case "7":
				addJetMenu();
				break;
			case "8":
				removeJet();
				break;
			case "9":
				wantsToQuit = true;
			default:
				System.out.println("Invalid choice, try again.");
				break;
			}
		}
	}
	
	private void listFleet() {
		//calls the toString for each jet currently in the airField
		System.out.println(airField.toString());
//		List <Jet> fleet = airField.getFleet();
//		for (Jet jet : fleet) {
//			System.out.println(jet);
//		}
	}
	
	private void flyAll() {
		//iterates through each jet and calls its fly method
		List <Jet> fleet = airField.getFleet();
		for (Jet jet : fleet) {
			jet.fly();
		}
		System.out.println();
	}
	
	private String fastestJet() {
		double speed = 0;                         // placeholder to compare each speed to the next
		String fastest = null;                    // placeholder toString for the fastest
		double mach = 0;                          // placeholder for mach conversion
		String fastestAndMach = null;             // to be returned with fastest jet and mach conversion
		
		//checks each jet in the fleet and compares speeds
		List <Jet> fleet = airField.getFleet();
		for(int i = 0; i < fleet.size(); i++) {
			double currentSpeed = fleet.get(i).getSpeed();     //speed of jet in current index
			if(currentSpeed > speed) {
				speed = currentSpeed;
				fastest = fleet.get(i).toString();             //current fastest toString
				mach = fleet.get(i).getSpeedInMach();          //current fastest mach conversion
				fastestAndMach = fastest + "Which converts to mach " + mach + "!";
			}
		}
		return fastestAndMach;
	}
	
	private String longestRange() {
		double range = 0;                         // placeholder to compare each range to the next
		String longestRange = null;                    //placeholder toString for the longest range
		
		//checks each jet in the fleet and compares range
		List <Jet> fleet = airField.getFleet();
		for(int i = 0; i < fleet.size(); i++) {
			double currentRange = fleet.get(i).getRange();         //range of jet in current index
			if(currentRange > range) {
				range = currentRange;
				longestRange = fleet.get(i).toString();  		   //current longestRange toString
			}
		}
		return longestRange;
	}
		
	private void stealthMode() {
//		checks each jet in the fleet is a stealthPlane and then calls the interface
		String name = null;
		List <Jet> fleet = airField.getFleet();
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i) instanceof StealthPlane ) {
				name = fleet.get(i).getModel();
				StealthPlane stealthPlane = (StealthPlane) fleet.get(i);    //Downcast in order to call the method for stealthPlane
				 System.out.print(name + ": ");
				 stealthPlane.offTheRadar();
			}
		}
	}
	
	private void dogFight() {
//		checks each jet in the fleet is a stealthPlane and then calls the interface
		String name = null;
		List <Jet> fleet = airField.getFleet();
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i) instanceof FighterJet ) {
				name = fleet.get(i).getModel();
				FighterJet fighterJet = (FighterJet) fleet.get(i);    //Downcast in order to call the method for stealthPlane
				System.out.print(name + ": ");
				 fighterJet.fight();
			}
		}
	}
	
	private void addJetMenu() {
		boolean wantsToAddJet = true;
		while (wantsToAddJet) {
			System.out.println("Which type of Jet would you like to add? \n"
							 + "1) Fighter Jet \n"
							 + "2) Stealth Plane \n"
							 + "3) Passenger Plane \n"
							 + "4) Quit");
			String whichJet = scanner.nextLine();
			
			switch(whichJet) {
			case "1":
				addFighterJet();
				break;
			case "2":
				addStealthPlane();
				break;
			case "3":
				addPassengerPlane();
				break;
			case "4":
				wantsToAddJet = false;
			default:
				System.out.println("Invalid choice, try again.");
			}
		}
	}
		
	private void addFighterJet() {
		
	}
	
	private void addStealthPlane() {
		
	}
	
	private void addPassengerPlane() {
		
	}
	
	
	private void removeJet() {
		
	}
	
	private void cleanUp() {
		scanner.close();
	}

}
