package view;

public class DisplayController {
    private static DisplayController displayController;
    private Displayable currentDisplay;

    private DisplayController(Displayable initialDisplay) {
        currentDisplay = initialDisplay;
    }

    public static DisplayController getInstance(Displayable initialDisplay) {
        if (displayController == null) {
            displayController = new DisplayController(initialDisplay);
        }
        return displayController;
    }

    public static DisplayController getInstance() {
        if (displayController == null) {
            throw new IllegalStateException(
                    "Instance not initialized! Specify initial Displayable object to initialize instance.");
        }
        return displayController;
    }

    public void displayCurrent() {
        while (currentDisplay != null) {
            Displayable temp = currentDisplay;
            currentDisplay = null;
            temp.display();
        }
    }

    public void setNextDisplay(Displayable displayable) {
        currentDisplay = displayable;
    }
}
