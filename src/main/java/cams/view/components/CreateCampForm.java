package cams.view.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

import cams.camp.CampController;
import cams.domain.StaffController;
import cams.view.DisplayController;
import cams.view.base.Alert;
import cams.view.base.Form;
import cams.view.base.ItemAction;
import cams.view.base.TextBox;

public class CreateCampForm extends Form {
    private boolean parseVisibility(String visibility) {
        if (visibility.equalsIgnoreCase("n"))
            return false;
        return true;
    }

    public CreateCampForm(Scanner scanner) {
        super(scanner);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        setTitle("Create a new Camp:\n");

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
                CampController campController = CampController.getInstance();
                StaffController staffController = StaffController.getInstance();
                DisplayController displayController = DisplayController.getInstance();

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
                            new CreateCampForm(scanner),
                            scanner));

                } catch (NumberFormatException e) {
                    displayController.setNextDisplay(new Alert(
                            "Number input format is invalid!",
                            new CreateCampForm(scanner),
                            scanner));
                }
            }
        });
    }
}
