package view;

import java.util.Scanner;

public class Alert extends ActionableItem implements Displayable {
    private Scanner scanner;

    public Alert(String content, Displayable previousDisplayable, Scanner scanner) {
        super(content, new ItemAction() {
            public void execute() {
                DisplayController displayController = DisplayController.getInstance();
                displayController.setNextDisplay(previousDisplayable);
            }
        });
        this.scanner = scanner;
    }

    public void display() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(getContent());
        System.out.println("\nPress ENTER to continue...");
        try {
            System.in.read();
            scanner.nextLine();
        } catch (Exception e) {
        }
        runAction();
    }
}
