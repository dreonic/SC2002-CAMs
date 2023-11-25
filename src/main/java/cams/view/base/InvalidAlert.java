package cams.view.base;

import java.util.Scanner;

/**
 * Dismissible pop-up when user selects an invalid choice in a menu.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class InvalidAlert extends Alert {
    /**
     * Class constructor specifying caller user interface element and the scanner to
     * be used to receive user input.
     *
     * @param previousDisplayable caller user interface element
     * @param scanner             scanner for this alert
     */
    public InvalidAlert(Displayable previousDisplayable, Scanner scanner) {
        super("Invalid choice!", previousDisplayable, scanner);
    }
}
