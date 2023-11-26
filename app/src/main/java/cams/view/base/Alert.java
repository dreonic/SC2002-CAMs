package cams.view.base;

import cams.view.CommonElements;
import cams.view.DisplayController;

import java.util.Scanner;

/**
 * Base class for all dismissible pop-up user interface elements. This base
 * class enables displaying a pop-up with content which can be dismissed by
 * pressing the ENTER key. Once dismissed, the previous/calling
 * {@link Displayable} user interface element is displayed. Specific
 * alerts in this application like {@link InvalidAlert} are implemented as
 * extensions from this base class.
 * <p>
 * To use this base alert, extend from or construct this base class with the
 * content and specify the previous {@link Displayable} which called this
 * alert.
 * Usage example by extension:
 *
 * <pre>
 * {@code
 * public class SpecificAlert extends Alert {
 *     public SpecificAlert(Displayable previousDisplayable, Scanner scanner) {
 *         super("Alert content", previousDisplayable, scanner);
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
 *         Alert alert = new Alert("Alert content", previousDisplayable, sc);
 *     }
 * }
 * }
 * </pre>
 * <p>
 * The result of the example above:
 *
 * <pre>
 * Alert Content
 *
 * Press ENTER to continue...
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
public class Alert extends ActionableItem implements Displayable {
    private final Scanner scanner;

    /**
     * Constructs alert with content, caller user interface element
     * and the scanner to be used to receive user input.
     *
     * @param content             displayed content
     * @param previousDisplayable caller user interface element
     * @param scanner             scanner for this alert
     */
    public Alert(String content, final Displayable previousDisplayable, Scanner scanner) {
        super(content, new ItemAction() {
            public void execute() {
                DisplayController displayController = DisplayController.getInstance();
                displayController.setNextDisplay(previousDisplayable);
            }
        });
        this.scanner = scanner;
    }

    /**
     * Clears the standard output and displays content and instructions to dismiss
     * pop-up. Dismissing the pop-up using the ENTER eky will return the user to the
     * previous <code>Displayable</code> user interface element.
     */
    public void display() {
        CommonElements.clearSystemOut();
        System.out.println(getContent());
        System.out.println("\nPress ENTER to continue...");
        try {
            scanner.nextLine();
        } catch (Exception ignored) {
        }
        runAction();
    }
}
