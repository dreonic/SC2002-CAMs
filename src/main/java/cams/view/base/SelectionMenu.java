package cams.view.base;

import cams.view.DisplayController;
import cams.view.components.InvalidAlert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Base class for all user interface elements with actionable selections. This
 * base class enables displaying a prompt alongside several selections with
 * distinct actions from which the user can choose one. Specific selection menus
 * in this application like {@code WelcomeMenu} are implemented as
 * extensions from this base class.
 * <p>
 * To use this base selection menu, extend from or construct this base class,
 * set the prompt and add the appropriate selection items, each with their own
 * content and action. The order of items added corresponds to the order in
 * which they are displayed in the standard output.
 * <p>
 * Usage example by extension:
 *
 * <pre>
 * {@code
 * public class SpecificMenu extends SelectionMenu {
 *     public SpecificMenu(Scanner scanner) {
 *         super(scanner);
 *         setPrompt("Prompt content goes here.");
 *
 *         // Will appear as the first selection choice
 *         addItem(new ActionableItem("Selection item 1", new ItemAction() {
 *             public void execute() {
 *                 System.out.println("Item action 1");
 *             }
 *         }));
 *
 *         // Will appear as the second selection choice
 *         addItem(new ActionableItem("Selection item 2", new ItemAction() {
 *             public void execute() {
 *                 System.out.println("Item action 2");
 *             }
 *         }));
 *     }
 * }
 * }
 * </pre>
 * <p>
 * Usage example by association:
 *
 * <pre>
 * {@code
 * public class Foo {
 *     public void bar() {
 *         Scanner sc = new Scanner(System.in);
 *         SelectionMenu menu = new SelectionMenu(sc);
 *         menu.setPrompt("Prompt content goes here.");
 *
 *         // Will appear as the first selection choice
 *         menu.addItem(new ActionableItem("Selection item 1", new ItemAction() {
 *             public void execute() {
 *                 System.out.println("Item action 1");
 *             }
 *         }));
 *
 *         // Will appear as the second selection choice
 *         menu.addItem(new ActionableItem("Selection item 2", new ItemAction() {
 *             public void execute() {
 *                 System.out.println("Item action 2");
 *             }
 *         }));
 *     }
 * }
 * }
 * </pre>
 * <p>
 * The result of the example above:
 *
 * <pre>
 * Prompt content goes here.
 * (1) Selection item 1
 * (2) Selection item 2
 * Choice:
 * </pre>
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class SelectionMenu implements Displayable {
    private final List<ActionableItem> items;
    private final Scanner scanner;
    private String prompt;

    /**
     * Constructs an empty menu with the scanner to be used to receive user input.
     *
     * @param scanner scanner for this menu
     */
    public SelectionMenu(Scanner scanner) {
        prompt = "";
        this.scanner = scanner;
        items = new ArrayList<ActionableItem>();
    }

    /**
     * Constructs a menu with prompt and the scanner to be used to receive user
     * input.
     *
     * @param prompt  displayed prompt
     * @param scanner scanner for this menu
     */
    public SelectionMenu(String prompt, Scanner scanner) {
        this.prompt = prompt;
        this.scanner = scanner;
        items = new ArrayList<ActionableItem>();
    }

    /**
     * Retrieves this menu's prompt.
     *
     * @return menu prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Assigns this menu's prompt.
     *
     * @param prompt prompt to be displayed
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Adds a new actionable selection to the menu. Actionable selections added to
     * the menu cannot be removed afterward.
     *
     * @param item actionable selection
     */
    public void addItem(ActionableItem item) {
        items.add(item);
    }

    /**
     * Clears the standard output and displays menu prompt and selections to the
     * standard output. Waits and captures user selection choice and executes the
     * action of the selected item afterward.
     */
    public void display() {
        int choice = 0;

        CommonElements.clearSystemOut();
        System.out.println(getPrompt());
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("(%d) ", i + 1);
            System.out.println(items.get(i).getContent());
        }

        System.out.print("Choice: ");
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
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
