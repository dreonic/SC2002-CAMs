package cams.view.components;

import java.util.Scanner;

import cams.view.base.Alert;
import cams.view.base.Displayable;

public class LoginErrorAlert extends Alert {
    public LoginErrorAlert(Displayable previousDisplayable, Scanner scanner) {
        super("Incorrect user ID or password!", previousDisplayable, scanner);
    }
}
