package view;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements Displayable {
    private String prompt;
    private ArrayList<ActionableItem> items;
    private Scanner scanner;

    public Menu(Scanner scanner) {
        prompt = "";
        this.scanner = scanner;
        items = new ArrayList<ActionableItem>();
    }

    public Menu(String prompt, Scanner scanner) {
        this.prompt = prompt;
        this.scanner = scanner;
        items = new ArrayList<ActionableItem>();
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void addItem(ActionableItem item) {
        items.add(item);
    }

    public void display() {
        int choice = 0;
        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(getPrompt());
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("(%d) ", i + 1);
                System.out.println(items.get(i).getContent());
            }
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            if (choice > 0 && choice <= items.size()) {
                items.get(choice - 1).runAction();
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        } while (choice <= 0 || choice > items.size());
    }
}