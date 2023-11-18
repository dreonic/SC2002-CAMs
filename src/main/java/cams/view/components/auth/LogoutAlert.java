package cams.view.components.auth;

import cams.view.base.Alert;
import cams.view.components.WelcomeMenu;

import java.util.Scanner;

public class LogoutAlert extends Alert {
    public LogoutAlert(Scanner scanner) {
        super("Logged out successfully.", new WelcomeMenu(scanner), scanner);
    }
}
