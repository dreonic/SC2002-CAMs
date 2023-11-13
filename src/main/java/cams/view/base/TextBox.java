package cams.view.base;

import java.io.Console;
import java.util.Scanner;

/**
 * Base class for user interface elements which requests user input. This base
 * class enables this application to retrieve and store user input which may be
 * further processed by classes containing this base class. This base textbox is
 * used by association in this application.
 * <p>
 * To use this base textbox, construct this base class with its label.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class TextBox implements Displayable {
    private String label;
    private String value;
    private Scanner scanner;
    private boolean inputHidden;

    /**
     * Constructs a labelled textbox and the scanner to be used to receive user
     * input. Label should be descriptive of what is required from the user.
     * 
     * @param label   textbox label
     * @param scanner scanner for this textbox
     */
    public TextBox(String label, Scanner scanner) {
        this.label = label;
        this.scanner = scanner;
        inputHidden = false;
        value = "";
    }

    /**
     * Constructs a labelled textbox where the user input can be hidden and the
     * scanner to be used to receive user input. Label should be descriptive of what
     * is required from the user. An example where user input may want to be hidden
     * is when asking for the user's password.
     * 
     * @param label       textbox label
     * @param inputHidden disables input echoing if true
     * @param scanner     scanner for this textbox
     */
    public TextBox(String label, boolean inputHidden, Scanner scanner) {
        this.label = label;
        this.scanner = scanner;
        this.inputHidden = inputHidden;
        value = "";
    }

    /**
     * Retrieves textbox label.
     * 
     * @return textbox label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Assigns the textbox label
     * 
     * @param label textbox label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Retrieves textbox value/user input.
     * 
     * @return textbox value
     */
    public String getValue() {
        return value;
    }

    /**
     * Displays textbox label to the standard output and gets user input in the same
     * line using the provided scaanner of the textbox.
     */
    public void display() {
        Console console = System.console();
        System.out.print(label + ": ");
        if (inputHidden) {
            value = new String(console.readPassword());
        } else {
            value = scanner.nextLine();
        }
    }
}
