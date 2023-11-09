package view;

import java.util.Scanner;

public class WelcomeMenu extends Menu {
    private String header = """
             ██████╗ █████╗ ███╗   ███╗███████╗
            ██╔════╝██╔══██╗████╗ ████║██╔════╝
            ██║     ███████║██╔████╔██║███████╗
            ██║     ██╔══██║██║╚██╔╝██║╚════██║
            ╚██████╗██║  ██║██║ ╚═╝ ██║███████║
             ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
                       """;

    private String separator = String.format("%040d", 0).replace("0", "═") + "\n";

    public WelcomeMenu(Scanner scanner) {
        super(scanner);

        setPrompt(separator + "\n" + header + "\n" + separator
                + "Welcome to Camp Application and Management System (CAMs).\n");

        addItem(new ActionableItem("Login", new ItemAction() {
            public void execute() {
                DisplayController menuController = DisplayController.getInstance();
                menuController.setNextDisplay(new LoginMenu(scanner));
            }
        }));

        addItem(new ActionableItem("Exit", new ItemAction() {
            public void execute() {
                return;
            }
        }));
    }
}