package view;

import java.util.Scanner;

/**
 * User login interface.
 * 
 * @author  Gillbert Susilo Wong
 * @author  Juan Frederick
 * @author  Karl Devlin Chau
 * @author  Pascalis Pandey
 * @author  Trang Nguyen
 * @version 1.0
 * @since   2023-11-09
 */
public class LoginMenu extends Menu {
    /**
     * Class constructor specifying the scanner to be used to receive user input.
     * 
     * @param scanner scanner for this menu
     */
    public LoginMenu(Scanner scanner) {
        super("Login to CAMS", scanner);

        addItem(new ActionableItem("Return to Main Menu", new ItemAction() {
            public void execute() {
                DisplayController displayController = DisplayController.getInstance();
                displayController.setNextDisplay(new WelcomeMenu(scanner));
            }
        }));
    }
}
