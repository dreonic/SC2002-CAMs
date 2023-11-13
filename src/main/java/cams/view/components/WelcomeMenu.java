package cams.view.components;

import java.util.Scanner;

import cams.user.AuthController;
import cams.user.UserController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
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
    private String header = """
                 ██████╗ █████╗ ███╗   ███╗███████╗
                ██╔════╝██╔══██╗████╗ ████║██╔════╝
                ██║     ███████║██╔████╔██║███████╗
                ██║     ██╔══██║██║╚██╔╝██║╚════██║
                ╚██████╗██║  ██║██║ ╚═╝ ██║███████║
                 ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
            """;

    private String separator = String.format("%045d", 0).replace("0", "═") + "\n";

    /**
     * Class constructor specifying the scanner to be used to receive user input.
     * 
     * @param scanner scanner for this menu
     */
    public WelcomeMenu(Scanner scanner) {
        super(scanner);

        setPrompt(separator + "\n" + header + "\n" + separator
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