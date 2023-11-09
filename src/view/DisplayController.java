package view;

/**
 * Singleton class for managing and navigating all user interface elements.
 * 
 * @author  Gillbert Susilo Wong
 * @author  Juan Frederick
 * @author  Karl Devlin Chau
 * @author  Pascalis Pandey
 * @author  Trang Nguyen
 * @version 1.0
 * @since   2023-11-09
 */
public class DisplayController {
    private static DisplayController displayController;
    private Displayable currentDisplay;

    private DisplayController(Displayable initialDisplay) {
        currentDisplay = initialDisplay;
    }

    /**
     * Instantiates singleton with inital user interface element. 
     * 
     * @param initialDisplay initial user interface element
     * @return singleton instance
     */
    public static DisplayController getInstance(Displayable initialDisplay) {
        if (displayController == null) {
            displayController = new DisplayController(initialDisplay);
        }
        return displayController;
    }

    /**
     * Gets singleton instance.
     * 
     * @return singleton instance
     * @throws IllegalStateException when singleton has not yet been initialized with an initial user interface element
     */
    public static DisplayController getInstance() throws IllegalStateException {
        if (displayController == null) {
            throw new IllegalStateException(
                    "Instance not initialized! Specify initial Displayable object to initialize instance.");
        }
        return displayController;
    }

    /**
     * Displays assigned user interface element.
     */
    public void displayCurrent() {
        while (currentDisplay != null) {
            Displayable temp = currentDisplay;
            currentDisplay = null;
            temp.display();
        }
    }

    /**
     * Sets the user interface element to be displayed next.
     * 
     * @param displayable user interface element to be displayed
     */
    public void setNextDisplay(Displayable displayable) {
        currentDisplay = displayable;
    }
}
