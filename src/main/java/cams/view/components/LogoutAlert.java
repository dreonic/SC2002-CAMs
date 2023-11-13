package cams.view.components;

import java.util.Scanner;

import cams.view.base.Alert;

public class LogoutAlert extends Alert {
    public LogoutAlert(Scanner scanner) {
        super("Logged out successfully.", new WelcomeMenu(scanner), scanner);
    }
}
