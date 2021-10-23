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
		
	}
	
	private void cleanUp() {
		scanner.close();
	}

}
