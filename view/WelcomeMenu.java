package view;

import java.util.Scanner;

public class WelcomeMenu extends Menu {
    public WelcomeMenu(Scanner scanner) {
        super("Welcome to CAMS menu demo!", scanner);

        addItem(new MenuItem("Login", new MenuItemAction() {
            public void execute() {
                MenuController menuController = MenuController.getInstance();
                menuController.setCurrentMenu(new LoginMenu(scanner));
                menuController.displayCurrentMenu();
            }
        }));

        addItem(new MenuItem("Exit", new MenuItemAction() {
            public void execute() {
                return;
            }
        }));
    }
}
