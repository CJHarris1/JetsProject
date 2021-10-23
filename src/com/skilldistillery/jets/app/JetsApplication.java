package com.skilldistillery.jets.app;

import java.util.Scanner;

import com.skilldistillery.jets.entities.AirField;


public class JetsApplication {
	private AirField airField;
	private Scanner scanner = new Scanner(System.in);
	
	
	public JetsApplication() {}
	

	public static void main(String[] args) {
		JetsApplication jetApp = new JetsApplication();
		
		jetApp.launch();
		jetApp.displayUserMenu();
		jetApp.cleanUp();
	}
	
	private void launch() {
		
	}
	
	private void displayUserMenu() {
		
	}
	
	private void cleanUp() {
		scanner.close();
	}

}
