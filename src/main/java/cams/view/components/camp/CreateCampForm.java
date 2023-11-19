package cams.view.components.camp;

import cams.camp.CampController;
import cams.domain.StaffController;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;
import cams.view.components.staff.StaffMenu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class CreateCampForm extends Form {
    public CreateCampForm(Scanner scanner) {
        super(scanner);

        CampController campController = CampController.getInstance();
        StaffController staffController = StaffController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        setTitle(CommonElements.getStatusBar("Create a Camp"));

        addInput(new TextBox("Camp Name", scanner));
        addInput(new TextBox("Location", scanner));
        addInput(new TextBox("Description", scanner));
        addInput(new TextBox("Start Date (dd-mm-yyyy)", scanner));
        addInput(new TextBox("End Date (dd-mm-yyyy)", scanner));
        addInput(new TextBox("Registration Closing Date (dd-mm-yyyy)", scanner));
        addInput(new TextBox("Total Slots", scanner));
        addInput(new TextBox("User Group", scanner));
        addInput(new TextBox("Make visible? (Y/n)", scanner));

        setAction(new ItemAction() {
            public void execute() {
                Map<String, String> values = getValues();

                try {
                    campController.createCamp(
                            values.get("Camp Name"),
                            values.get("Location"),
                            values.get("Description"),
                            LocalDate.parse(values.get(
                                    "Start Date (dd-mm-yyyy)"), formatter),
                            LocalDate.parse(values.get(
                                    "End Date (dd-mm-yyyy)"), formatter),
                            LocalDate.parse(values.get(
                                            "Registration Closing Date (dd-mm-yyyy)"),
                                    formatter),
                            Integer.parseInt(values.get("Total Slots")),
                            parseVisibility(values.get("Make visible? (Y/n)")),
                            values.get("User Group"),
                            staffController.getCurrentStaff());

                    displayController.setNextDisplay(new StaffMenu(scanner));

                } catch (DateTimeParseException e) {
                    displayController.setNextDisplay(new Alert(
                            "Date input format is invalid!",
                            new StaffMenu(scanner),
                            scanner));

                } catch (NumberFormatException e) {
                    displayController.setNextDisplay(new Alert(
                            "Number input format is invalid!",
                            new StaffMenu(scanner),
                            scanner));

                } catch (IllegalArgumentException e) {
                    displayController.setNextDisplay(new Alert(
                            "Registration should not end after start date and start date should not end after end date!",
                            new StaffMenu(scanner), scanner));
                }
            }
        });
    }

    private boolean parseVisibility(String visibility) {
        return !visibility.equalsIgnoreCase("n");
    }
}
