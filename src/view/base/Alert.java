package view.base;

import java.util.Scanner;

import view.DisplayController;

/**
 * Base class for all dismissable pop-up user interface elements.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class Alert extends ActionableItem implements Displayable {
    private Scanner scanner;

    /**
     * Class constructor specifying displayed content, caller user interface element
     * and the scanner to be used to receive user input.
     * 
     * @param content             displayed content
     * @param previousDisplayable caller user interface element
     * @param scanner             scanner for this alert
     */
    public Alert(String content, Displayable previousDisplayable, Scanner scanner) {
        super(content, new ItemAction() {
            public void execute() {
                DisplayController displayController = DisplayController.getInstance();
                displayController.setNextDisplay(previousDisplayable);
            }
        });
        this.scanner = scanner;
    }

    /**
     * Clears stdout and displays content and instructions to dismiss pop-up.
     */
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
