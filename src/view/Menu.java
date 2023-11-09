package view;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Base class for all user interface elements with actionable selections.
 * 
 * @author  Gillbert Susilo Wong
 * @author  Juan Frederick
 * @author  Karl Devlin Chau
 * @author  Pascalis Pandey
 * @author  Trang Nguyen
 * @version 1.0
 * @since   2023-11-09
 */
public class Menu implements Displayable {
    private String prompt;
    private ArrayList<ActionableItem> items;
    private Scanner scanner;

    /**
     * Class constructor specifying the scanner to be used to receive user input.
     * 
     * @param scanner scanner for this menu
     */
    public Menu(Scanner scanner) {
        prompt = "";
        this.scanner = scanner;
        items = new ArrayList<ActionableItem>();
    }

    /**
     * Class constructor specifying the displayed prompt and the scanner to be used to receive user input.
     * 
     * @param prompt displayed prompt
     * @param scanner scanner for this menu
     */
    public Menu(String prompt, Scanner scanner) {
        this.prompt = prompt;
        this.scanner = scanner;
        items = new ArrayList<ActionableItem>();
    }

    /**
     * Retrieves menu prompt.
     * 
     * @return menu prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Assigns menu prompt.
     * 
     * @param prompt prompt to be displayed
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Adds a new actionable selection to the menu.
     * 
     * @param item actionable selection
     */
    public void addItem(ActionableItem item) {
        items.add(item);
    }

    /**
     * Clears stdout and displays prompt and selections to stdout. Gets user selection choice and executes the action of the selected item.
     */
    public void display() {
        int choice = 0;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(getPrompt());
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("(%d) ", i + 1);
            System.out.println(items.get(i).getContent());
        }
        System.out.print("Choice: ");
        try {
        choice = scanner.nextInt();
        } catch (Exception e) {
            DisplayController displayController = DisplayController.getInstance();
            displayController.setNextDisplay(new InvalidAlert(this, scanner));
        }
        if (choice > 0 && choice <= items.size()) {
            items.get(choice - 1).runAction();
        } else {
            DisplayController displayController = DisplayController.getInstance();
            displayController.setNextDisplay(new InvalidAlert(this, scanner));
        }
    }
}