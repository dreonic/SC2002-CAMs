package cams.view.components.student;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.serializer.StudentListSerializer;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

import java.util.Scanner;

public class StudentStudentListFilterMenu extends SelectionMenu {
    public StudentStudentListFilterMenu(Scanner scanner) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = CampController.getInstance().getCurrentCamp();
        String campName = camp.getCampInfo().getCampName();

        addItem(new ActionableItem("Generate attendee list only.", new ItemAction() {
            public void execute() {
                StudentListSerializer.serialize(camp, "committee");
                displayController.setNextDisplay(new Alert(
                        "Student list generated! See report in /student_list_" + campName + ".xlsx",
                        new StudentCommitteeMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Generate committee list only.", new ItemAction() {
            public void execute() {
                StudentListSerializer.serialize(camp, "attendee");
                displayController.setNextDisplay(new Alert(
                        "Student list generated! See report in /student_list_" + campName + ".xlsx",
                        new StudentCommitteeMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Generate both.", new ItemAction() {
            public void execute() {
                StudentListSerializer.serialize(camp, "none");
                displayController.setNextDisplay(new Alert(
                        "Student list generated! See report in /student_list_" + campName + ".xlsx",
                        new StudentCommitteeMenu(scanner), scanner));

            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
            }
        }));
    }

}