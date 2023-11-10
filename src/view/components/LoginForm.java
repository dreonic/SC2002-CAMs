package view.components;

import java.util.Scanner;

import view.base.Form;
import view.base.TextBox;

/**
 * User login interface.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class LoginForm extends Form {
    /**
     * Class constructor specifying the scanner to be used to receive user input.
     * 
     * @param scanner scanner for this menu
     */
    public LoginForm(Scanner scanner) {
        super("Login to CAMS", scanner);

        addInput(new TextBox("User ID", scanner));
        addInput(new TextBox("Password", true, scanner));
    }
}
