package view;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String prompt;
    private ArrayList<MenuItem> items;
    private Scanner scanner;

    public Menu(Scanner scanner) {
        prompt = "";
        this.scanner = scanner;
        items = new ArrayList<MenuItem>();
    }

    public Menu(String prompt, Scanner scanner) {
        this.prompt = prompt;
        this.scanner = scanner;
        items = new ArrayList<MenuItem>();
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void display() {
        int choice = 0;
        do {
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