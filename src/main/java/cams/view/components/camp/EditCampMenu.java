package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.*;
import cams.view.components.staff.StaffCampMenu;

import java.util.Scanner;

/**
 * The boundary class displaying the menu for a staff to edit camp details. Camp details are edited
 * by selecting the options within this menu.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class EditCampMenu extends SelectionMenu {
    /**
     * Constructs the camp editing menu with the scanner used to obtain user input. Displays an
     * alert when editing a nonempty camp's name. Displays an alert to confirm visibility has
     * changed when toggling visibility.
     *
     * @param scanner scanner for this menu
     */
    public EditCampMenu(Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();

        setPrompt(CommonElements.getStatusBar("Editing " + camp.getCampInfo().getCampName()));

        addItem(new ActionableItem("Camp Name", new ItemAction() {
            public void execute() {
                if (camp.getAttendees().isEmpty()) {
                    displayController.setNextDisplay(new EditNameForm(scanner));
                } else {
                    displayController.setNextDisplay(new Alert("Cannot edit camp name!", new EditCampMenu(scanner), scanner));
                }
            }
        }));

        addItem(new ActionableItem("Location", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new EditLocationForm(scanner));
            }
        }));

        addItem(new ActionableItem("Description", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new EditDescriptionForm(scanner));
            }
        }));

        addItem(new ActionableItem("Toggle Visibility", new ItemAction() {
            public void execute() {
                CampEditor campEditor = new CampEditor(camp);
                campEditor.toggleVisibility();
                if (camp.getCampInfo().getIsVisible()) {
                    displayController.setNextDisplay(new Alert(
                            "Camp is now visible",
                            new EditCampMenu(scanner),
                            scanner));
                } else {
                    displayController.setNextDisplay(new Alert(
                            "Camp is now not visible",
                            new EditCampMenu(scanner),
                            scanner));
                }
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StaffCampMenu(scanner));
            }
        }));
    }
}
