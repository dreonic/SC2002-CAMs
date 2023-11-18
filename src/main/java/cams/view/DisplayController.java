package cams.view;

import cams.view.base.Displayable;

/**
 * A singleton class for managing and navigating all user interface elements. This class controls
 * the current and the subsequent user interface elements displayed to the user through standard
 * output.
 * <p>
 * To use this controller, first instantiate and initialize this singleton.
 *
 * <pre>
 * {@code
 * Scanner sc = new Scanner(System.in);
 * DisplayController controller = DisplayController.getInstance(new WelcomeMenu(sc));
 * }
 * </pre>
 * <p>
 * Once instantiated for the first time, the singleton instance can be retrieved through the
 * <code>getInstance</code> method without passing any arguments.
 *
 * <pre>
 * {@code
 * DisplayController controller = DisplayController.getInstance();
 * }
 * </pre>
 * <p>
 * Call the <code>displayCurrent</code> method of this singleton instance to start displaying user
 * interface elements. This call will terminate once there are no user interface elements available
 * to display.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class DisplayController {
    private static DisplayController displayController;
    private Displayable currentDisplay;

    private DisplayController(Displayable initialDisplay) {
        currentDisplay = initialDisplay;
    }

    /**
     * Instantiates and initializes singleton with inital user interface element. This user
     * interface element will be displayed first upon calling <code>displayCurrent</code> unless
     * overridden by the setter <code>setNextDisplay</code> before first call.
     *
     * @param initialDisplay initial user interface element if the singleton has not been
     *                       instantiated; otherwise, this is unused
     * @return singleton instance
     */
    public static DisplayController getInstance(Displayable initialDisplay) {
        if (displayController == null) {
            displayController = new DisplayController(initialDisplay);
        }
        return displayController;
    }

    /**
     * Returns an initialized singleton instance.
     *
     * @return singleton instance
     * @throws IllegalStateException when singleton has not yet been initialized with an initial
     *                               user interface element
     */
    public static DisplayController getInstance() throws IllegalStateException {
        if (displayController == null) {
            throw new IllegalStateException(
                    "Instance not initialized! Specify initial Displayable object to initialize instance.");
        }
        return displayController;
    }

    /**
     * Displays assigned user interface element. The first user interface element displayed is the
     * initial user interface element specified during instantiation. Subsequent displayed user
     * interface elements are specified through the setter <code>setNextDisplay</code>.
     * <p>
     * This method starts displaying user interface elements through the <code>display</code> method
     * of the <code>Displayable</code> interface until no user interface elements are available to
     * display. Once none is available to display, this method terminates.
     */
    public void displayCurrent() {
        while (currentDisplay != null) {
            Displayable temp = currentDisplay;
            currentDisplay = null;
            temp.display();
        }
    }

    /**
     * Sets the user interface element to be displayed next. This user interface element will be
     * displayed after the current user interface element display terminates if singleton is running
     * <code>displayCurrent</code>. Otherwise, this method sets the first user interface to display
     * upon calling <code>displayCurrent</code>.
     *
     * @param displayable user interface element to be displayed
     */
    public void setNextDisplay(Displayable displayable) {
        currentDisplay = displayable;
    }
}
