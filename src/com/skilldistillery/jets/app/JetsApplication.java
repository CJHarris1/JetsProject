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

	public JetsApplication() {
	}

	public static void main(String[] args) {
		JetsApplication jetApp = new JetsApplication();

		jetApp.launch();
		System.out.println("Welcome to the Harris air field!");
		jetApp.displayUserMenu();
		jetApp.cleanUp();
	}

	private void launch() {
		// reads through the jets file and separates out each line and enters into
		// fields
		try (BufferedReader bufIn = new BufferedReader(new FileReader("jets.txt"))) {
			String line;
			Jet j;
			while ((line = bufIn.readLine()) != null) {
				String[] jetRecord = line.split(",");
				String type = jetRecord[0];
				String model = jetRecord[1];
				double speed = Double.valueOf(jetRecord[2]);
				int range = Integer.parseInt(jetRecord[3]);
				long price = Long.valueOf(jetRecord[4]);

				// check which type of jet is being instantiated
				if (type.equals("FighterJet")) {
					j = new FighterJet(model, speed, range, price);
				} else if (type.equals("StealthPlane")) {
					j = new StealthPlane(model, speed, range, price);
				} else {
					j = new PassengerJet(model, speed, range, price);
				}
				jets.add(j);
			}
			airField.setFleet(jets); // sets the fleet field to the Collection of Jets from file
		}

		catch (IOException e) {
			System.err.println(e);
		}
	}

	private void displayUserMenu() {
		boolean wantsToQuit = false;
		while (!wantsToQuit) {
			System.out.println("Which option would you like? \n" + "1) List fleet \n" + "2) Fly all jets \n"
					+ "3) View fastest jet \n" + "4) View jet with longest range \n" + "5) Enter Stealth mode \n"
					+ "6) Dogfight! \n" + "7) Add a jet to Fleet \n" + "8) Remove a jet from Fleet \n" + "9) Quit \n");

			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				System.out.println("Harris AirField currently has these jets in the fleet: \n");
				airField.listFleet();
				break;
			case "2":
				airField.flyAll();
				break;
			case "3":
				System.out.println("The fastest jet information: \n" + airField.fastestJet() + "\n");
				break;
			case "4":
				System.out.println("The jet with the furthest range information: \n" + airField.longestRange());
				;
				break;
			case "5":
				airField.stealthMode();
				System.out.println(); // adding a space between the output and the next menu loop
				break;
			case "6":
				airField.dogFight();
				System.out.println(); // adding a space between the output and the next menu loop
				break;
			case "7":
				airField.addJet(scanner);
				airField.addAnotherJet(scanner);
				break;
			case "8":
				airField.removeJet(scanner);
				airField.removeAnotherJet(scanner);
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

	

	private void cleanUp() {
		scanner.close();
	}

}
