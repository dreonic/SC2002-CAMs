package cams.view.components.auth;

import java.util.Scanner;

import cams.view.base.Alert;
import cams.view.components.WelcomeMenu;

public class LogoutAlert extends Alert {
    public LogoutAlert(Scanner scanner) {
        super("Logged out successfully.", new WelcomeMenu(scanner), scanner);
    }
}
