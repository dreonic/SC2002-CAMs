package cams.view.components.camp;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.camp.CampEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.staff.StaffCampMenu;

import java.util.Scanner;

public class EditCampMenu extends SelectionMenu {
    public EditCampMenu(Scanner scanner) {
        super(scanner);

        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();

        setPrompt(CommonElements.getStatusBar("Editing " + camp.getCampInfo().getCampName()));

        addItem(new ActionableItem("Camp Name", new ItemAction() {
            public void execute() {
                if(camp.getAttendees().isEmpty()) {
                    displayController.setNextDisplay(new EditNameForm(scanner));
                }
                else {
                    displayController.setNextDisplay(new Alert("Cannot edit Camp name!", new EditCampMenu(scanner), scanner));
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
