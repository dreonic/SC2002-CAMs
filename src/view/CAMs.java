package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import user.AuthController;
import user.UserController;

public class CAMs extends View {
	public static void main(String args[]) {
		System.out.print("\033[H\033[2J");
		System.out.println("-----------------------------------------");
		System.out.println("\tWELCOME TO CAMs\t\t");
		System.out.println("-----------------------------------------");
		startAllManagers();
		start();
		closeAllManagers();
		System.out.println("-----------------------------------------");
		System.out.println("\tEXITING CAMs\t\t");
		System.out.println("-----------------------------------------");
	}

	private static void startAllManagers() {
		UserController.getUserController();
		AuthController.getAuthController();
	}

	private static void closeAllManagers() {
		UserController.close();
		AuthController.close();
	}

	public static void displayMenu() {
		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("\t\tMenu\t\t");
		System.out.println("--------------------------------------");
		System.out.println("Choice 1 : Login");
		System.out.println("Choice 2 : Exit CAMs");
		System.out.println("--------------------------------------");
	}
    
	public static void start() {
		sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			displayMenu();
			try {
				System.out.println("Enter Choice");
				choice = sc.nextInt();
				if (choice > 5 || choice < 1) {
					System.out.println("Invalid input!");
					// waitForEnter(null);
					continue;
				}
			} catch (InputMismatchException e) {
				inputMismatchHandler();
				// waitForEnter(null);
				continue;
			}
			switch (choice) {
				case 1:
					// authController.login();
					break;
				case 2:
					return;
				default:
					System.out.println("Invalid Choice Entered. Please Try Again.");
					choice = 0;
			}
		}
	}
}