package view;

import java.util.Scanner;

public class LoginMenu extends Menu {
    public LoginMenu(Scanner scanner) {
        super("Login to CAMS", scanner);

        addItem(new MenuItem("Exit", new MenuItemAction() {
            public void execute() {
                return;
            }
        }));
    }
}
