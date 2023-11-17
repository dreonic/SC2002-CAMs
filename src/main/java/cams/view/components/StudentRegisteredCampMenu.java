package cams.view.components;

import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StudentRegisteredCampMenu extends SelectionMenu {
    public StudentRegisteredCampMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        StudentController studentController = StudentController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        Student currentUser = studentController.getCurrentStudent();
        StringBuilder campInfo = new StringBuilder();

        campInfo.append("Camp Name: " + camp.getCampInfo().getCampName() + "\n");
        campInfo.append("Location: " + camp.getCampInfo().getLocation() + "\n");
        campInfo.append("Description: " + camp.getCampInfo().getDescription() + "\n");
        campInfo.append("Start Date: " + camp.getCampDate().getStartDate().toString() + "\n");
        campInfo.append("End Date: " + camp.getCampDate().getEndDate().toString() + "\n");
        campInfo.append("Available Slots: " + (camp.getCampInfo().getTotalSlots() - camp.getAttendees().size() - camp.getCommittee().size()) + "\n");
        campInfo.append("User Group: " + camp.getUserGroup() + "\n");
        campInfo.append("Staff in Charge: " + camp.getStaffInCharge().getName() + "\n");

        setPrompt(CommonElements.getStatusBar(camp.getCampInfo().getCampName()) + "\n" + campInfo.toString());

        addItem(new ActionableItem("Withdraw", new ItemAction() {
            public void execute() {
                currentUser.removeCamp(camp);
                camp.removeAttendee(currentUser);
                displayController.setNextDisplay(new Alert("Withdrawn from Camp successfully.", new StudentViewRegisteredCampMenu(scanner), scanner));
            }
        }));

        addItem(new ActionableItem("Submit Enquiry", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new SubmitEnquiryForm(scanner));
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentViewRegisteredCampMenu(scanner));
            }
        }));

    }
}
