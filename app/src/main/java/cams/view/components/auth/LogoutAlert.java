package cams.view.components.auth;

import cams.view.base.Alert;
import cams.view.components.WelcomeMenu;

import java.util.Scanner;

/**
 * Alerts user that the account is logged out.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */

public class LogoutAlert extends Alert {
    /**
     * Displays logout successful alert and returns to welcome menu using {@code WelcomeMenu()}.
     *
     * @param scanner scanner for this alert
     */
    public LogoutAlert(Scanner scanner) {
        super("Logged out successfully.", new WelcomeMenu(scanner), scanner);
    }
}
