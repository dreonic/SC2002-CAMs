package cams.view.base;

import java.io.Console;
import java.util.Scanner;

/**
 * Base class for user interface elements which requests user input. This base
 * class enables this application to retrieve and store user input which may be
 * further processed by classes containing this base class. This base text box is
 * used by association in this application.
 * <p>
 * To use this base text box, construct this base class with its label.
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
    private final String id;
    private final Scanner scanner;
    private final boolean inputHidden;
    private String label;
    private String value;

    /**
     * Constructs a labelled text box and the scanner to be used to receive user
     * input. The ID attached to this text box is the label. Label should be
     * descriptive of what is required from the user.
     *
     * @param label   text box label
     * @param scanner scanner for this text box
     */
    public TextBox(String label, Scanner scanner) {
        id = label;
        this.label = label;
        this.scanner = scanner;
        inputHidden = false;
        value = "";
    }

    /**
     * Constructs a labelled text box and the scanner to be used to receive user
     * input, with ID attached to the text box. Label should be descriptive of what
     * is required from the user.
     *
     * @param id      text box id
     * @param label   text box label
     * @param scanner scanner for this text box
     */
    public TextBox(String id, String label, Scanner scanner) {
        this.id = id;
        this.label = label;
        this.scanner = scanner;
        inputHidden = false;
        value = "";
    }

    /**
     * Constructs a labelled text box where the user input can be hidden and the
     * scanner to be used to receive user input. The ID attached to this text box is
     * the label. Label should be descriptive of what is required from the user. An
     * example where user input may want to be hidden is when asking for the user's
     * password.
     *
     * @param label       text box label
     * @param inputHidden disables input echoing if true
     * @param scanner     scanner for this text box
     */
    public TextBox(String label, boolean inputHidden, Scanner scanner) {
        id = label;
        this.label = label;
        this.scanner = scanner;
        this.inputHidden = inputHidden;
        value = "";
    }

    /**
     * Constructs a labelled text box where the user input can be hidden and the
     * scanner to be used to receive user input, with ID attached to the text box.
     * Label should be descriptive of what is required from the user. An example
     * where user input may want to be hidden is when asking for the user's
     * password.
     *
     * @param id          id of this text box; mainly used to get this text box's value within a
     *                    {@code Form}
     * @param label       text box label
     * @param inputHidden disables input echoing if true
     * @param scanner     scanner for this text box
     */
    public TextBox(String id, String label, boolean inputHidden, Scanner scanner) {
        this.id = id;
        this.label = label;
        this.scanner = scanner;
        this.inputHidden = inputHidden;
        value = "";
    }

    /**
     * Retrieves text box ID. This ID can be used by {@code Form} as the key to
     * access the text box's value.
     *
     * @return text box ID
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves text box label.
     *
     * @return text box label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Assigns the text box label
     *
     * @param label text box label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Retrieves text box value/user input.
     *
     * @return text box value
     */
    public String getValue() {
        return value;
    }

    /**
     * Displays text box label to the standard output and gets user input in the same
     * line using the provided scanner of the text box.
     */
    public void display() {
        Console console = System.console();
        System.out.print(label + ": ");
        if (inputHidden && console != null) {
            value = new String(console.readPassword());
        } else {
            value = scanner.nextLine();
        }
    }
}
