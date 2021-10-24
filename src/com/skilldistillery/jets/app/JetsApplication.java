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
	private List<Jet> jets = new ArrayList<>();
	
	
	public JetsApplication() {}
	

	public static void main(String[] args) {
		JetsApplication jetApp = new JetsApplication();
		
		jetApp.launch();
		System.out.println("Welcome to the Harris air field!");
		jetApp.displayUserMenu();
		jetApp.cleanUp();
	}

	private void launch() {
		//reads through the jets file and separates out each line and enters into fields
		try ( BufferedReader bufIn = new BufferedReader(new FileReader("jets.txt")) ) {
			  String line;
			  Jet j;
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
							 + "9) Quit \n");
	
		String choice = scanner.nextLine();
			switch(choice) {
			case "1":
				System.out.println("Harris AirField currently has these jets in the fleet: \n");
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
				addJet();
				addAnotherJet();
				break;
			case "8":
				removeJet();
				removeAnotherJet();
				break;
			case "9":
				System.out.println("Thank you for visiting the Harris air field!");
				wantsToQuit = true;
				break;
			default:
				System.out.println("Invalid choice, try again.");
				break;
			}
		}
	}
	
	private void listFleet() {
		//calls the toString for each jet currently in the airField
		List <Jet> fleet = airField.getFleet();
		for (Jet jet : fleet) {
			System.out.println(jet);
		}
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
//		checks each jet in the fleet if it is a stealthPlane and then calls the interface method
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
//		checks each jet in the fleet if it is a fighter jet and then calls the interface method
		String name = null;
		List <Jet> fleet = airField.getFleet();
		for(int i = 0; i < fleet.size(); i++) {
			if(fleet.get(i) instanceof FighterJet ) {
				name = fleet.get(i).getModel();
				FighterJet fighterJet = (FighterJet) fleet.get(i);    //Downcast in order to call the method for fighter jet
				System.out.print(name + ": ");
				 fighterJet.fight();
			}
		}
	}
	
	private void addJet() {
		boolean wantsToAddJet = true;
		String choseJet = null;
		
		while (wantsToAddJet) {
			boolean validJet = false;
			while(!validJet) {
				//loops to get valid choice of jet type and assigns choice to chose jet
				System.out.println("Which type of Jet is this? \n"
						 + "1) Fighter Jet \n"
						 + "2) Stealth Plane \n"
						 + "3) Passenger Jet \n");
				String whichJet = scanner.nextLine();
				switch(whichJet) {
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
			//retrieves all constructor fields to create new instance of a jet
			String model = getModelNewJet();
			double speed = getSpeedNewJet();
			int range = getRangeNewJet();
			long price = getPriceNewJet();
			
			//instantiates a jet depending on which type
			switch(choseJet) {
			case "fighter":
				Jet fighter = new FighterJet(model, speed, range, price);
				jets.add(fighter);
				wantsToAddJet = false;
				break;
			case "stealth":
				Jet stealth = new StealthPlane(model, speed, range, price);
				jets.add(stealth);
				wantsToAddJet = false;
				break;
			case "passenger":
				Jet passenger = new PassengerJet(model, speed, range, price);
				jets.add(passenger);
				wantsToAddJet = false;
				break;
			}
		}
	}
	
	private String getModelNewJet() {
		String model = null;
		boolean validModel = false;
		
		while(!validModel) {
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
	
	
	private double getSpeedNewJet() {
		double speed = 0;
		boolean validSpeed = false;
		
		while(!validSpeed) {
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
	
	
	private int getRangeNewJet() {
		int range = 0;
		boolean validRange = false;
		
		while(!validRange) {
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
	

	private long getPriceNewJet() {
		long price = 0;
		boolean validPrice = false;
		
		while(!validPrice) {
			System.out.print("What is the dollar ammount of this Jet? ");
			try {
				price = scanner.nextLong();
				scanner.nextLine(); // flush
				validPrice = true;
				
			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();			
			}
		}
		return price;
	}
	
	
	private void addAnotherJet() {
		// takes user input and either adds another jet, or goes back to main menu
		boolean addAnother = true;
		while(addAnother) {
			System.out.println("Jet has been added, would you like to add another? \n"
							 + "1) Yes, add another Jet \n"
							 + "2) No, take me back to the main menu \n");
			String addAnotherJet = scanner.nextLine();
	
			switch(addAnotherJet) {
			case "1":
				addJet();
				break;
			case "2":
				addAnother = false;
				break;
			default:
				System.out.println("Invalid choice, try again.");
			}
		}
	}
	
	
	private void removeJet() {
		int whichJetToRemove = -1;
		boolean removedJet = false;
		
		while(!removedJet) {
			System.out.println("Which Jet would you like to remove? ");
			for(int i = 0; i < airField.getFleet().size(); i++ ) {                    
				System.out.println("" + (i + 1) + ") " + airField.getFleet().get(i));  //prints out each jet in the fleet along with a number index for user to choose
				}
			try {
				whichJetToRemove = scanner.nextInt();
				scanner.nextLine();
				airField.getFleet().remove(whichJetToRemove - 1);  // removes jet at index user chose
				removedJet = true;
				
			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
			}
		}
	}
	
	private void removeAnotherJet() {
		// takes user input and either removes another jet, or goes back to main menu
		boolean removeAnother = true;
		while(removeAnother) {
			System.out.println("Jet has been removed, would you like to remove another? \n"
							 + "1) Yes, remove another Jet \n"
							 + "2) No, take me back to the main menu \n");
			String removeAnotherJet = scanner.nextLine();
	
			switch(removeAnotherJet) {
			case "1":
				removeJet();
				break;
			case "2":
				removeAnother = false;
				break;
			default:
				System.out.println("Invalid choice, try again.");
			}
		}
	}
	
	private void cleanUp() {
		scanner.close();
	}

}
