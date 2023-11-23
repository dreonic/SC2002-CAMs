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

/**
 * The boundary class responsible for displaying the options a committee member
 * has when they wish to generate a list of students tied to the specified Camp.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CommitteeStudentListFilterMenu extends SelectionMenu {

    /**
     * Constructs the Committee Student List Filter Menu specifying the scanner to be used.
     * 
     * @param scanner
     */
    public CommitteeStudentListFilterMenu(Scanner scanner) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = CampController.getInstance().getCurrentCamp();
        String campName = camp.getCampInfo().getCampName();

        addItem(new ActionableItem("Generate attendee list only.", new ItemAction() {
            public void execute() {
                StudentListSerializer.serialize(camp, "committee");
                displayController.setNextDisplay(new Alert(
                        "Student list generated! See report in report/student_list_" + campName + ".xlsx",
                        new StudentCommitteeMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Generate committee list only.", new ItemAction() {
            public void execute() {
                StudentListSerializer.serialize(camp, "attendee");
                displayController.setNextDisplay(new Alert(
                        "Student list generated! See report in report/student_list_" + campName + ".xlsx",
                        new StudentCommitteeMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Generate combined list.", new ItemAction() {
            public void execute() {
                StudentListSerializer.serialize(camp, "none");
                displayController.setNextDisplay(new Alert(
                        "Student list generated! See report in report/student_list_" + campName + ".xlsx",
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
