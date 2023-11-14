package cams.view.components;

import java.util.Scanner;

import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

/**
 * Initial user interface element displayed to the user.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class WelcomeMenu extends SelectionMenu {
    /**
     * Class constructor specifying the scanner to be used to receive user input.
     * 
     * @param scanner scanner for this menu
     */
    public WelcomeMenu(Scanner scanner) {
        super(scanner);

        setPrompt(CommonElements.getHeader()
                + "Welcome to Camp Application and Management System (CAMs).\n");

        addItem(new ActionableItem("Login", new ItemAction() {
            public void execute() {
                DisplayController menuController = DisplayController.getInstance();
                menuController.setNextDisplay(new LoginForm(scanner));
            }
        }));

        addItem(new ActionableItem("Exit", new ItemAction() {
            public void execute() {
                return;
            }
        }));
    }
}
