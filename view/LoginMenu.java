package view;

import java.util.Scanner;

public class LoginMenu extends Menu {
    public LoginMenu(Scanner scanner) {
        super("Login to CAMS", scanner);

        addItem(new ActionableItem("Exit", new ItemAction() {
            public void execute() {
                return;
            }
        }));
    }
}
