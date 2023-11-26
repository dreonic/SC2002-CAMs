package cams.view.components.auth;

import cams.view.base.Alert;
import cams.view.base.Displayable;

import java.util.Scanner;

/**
 * Alerts user when no user ID and password combination is found
 * in the database.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */

public class LoginErrorAlert extends Alert {
    /**
     * Displays invalid login credentials alert and redirects to the previous user interface.
     *
     * @param previousDisplayable caller user interface element
     * @param scanner             scanner for this alert
     */
    public LoginErrorAlert(Displayable previousDisplayable, Scanner scanner) {
        super("Incorrect user ID or password!", previousDisplayable, scanner);
    }
}
