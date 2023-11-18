package cams.view.components.auth;

import cams.view.base.Alert;
import cams.view.base.Displayable;

import java.util.Scanner;

public class LoginErrorAlert extends Alert {
    public LoginErrorAlert(Displayable previousDisplayable, Scanner scanner) {
        super("Incorrect user ID or password!", previousDisplayable, scanner);
    }
}
