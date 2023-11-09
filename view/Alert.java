package view;

import java.util.Scanner;

public class Alert extends ActionableItem implements Displayable {
    private Scanner scanner;

    public Alert(String content, ItemAction action, Scanner scanner) {
        super(content, action);
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
