package cams.view.components;

import java.util.ArrayList;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.view.base.ActionableItem;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;

public class StudentMenu extends SelectionMenu {
    public StudentMenu(Scanner scanner) {
        super(scanner);
        StudentController studentController = StudentController.getInstance();
        Student currentUser = studentController.getCurrentStudent();

        if (currentUser == null) {
            throw new IllegalStateException("No student has been assigned to the Student Controller class");
        }

        setPrompt(CommonElements.getHeader("Student")
                + "Welcome to CAMs, " + currentUser.getName() + "!\n");

        addItem(new ActionableItem("Change Password", new ItemAction() {
            public void execute() {

            }
        }));

        addItem(new ActionableItem("View Registered Camps", new ItemAction() { // need to make each listed Camp
                                                                               // interactable or assign index?
            public void execute() {
                ArrayList<Camp> registeredCamps = currentUser.getCamps();
                for (Camp camp : registeredCamps)
                    System.out.println(camp.getCampInfo().getCampName());
            }
        }));

        addItem(new ActionableItem("Withdraw from a Camp", new ItemAction() { // only accessible once user has selected
                                                                              // a Camp from 'View Registered Camps'
                                                                              // list
            public void execute() {

            }
        }));

        addItem(new ActionableItem("View Camps", new ItemAction() { // need to make each listed Camp interactable or
                                                                    // assign index?
            public void execute() {
                CampController campController = CampController.getInstance();
                ArrayList<Camp> allCamps = campController.getAllCamps();
                ArrayList<Camp> availableCamps = new ArrayList<Camp>();
                for (Camp camp : allCamps) { // add condition for dates OR check for clashing dates when student tries
                                             // to register?
                    if (camp.getCampInfo().getisVisible() == true && camp.getUserGroup() == currentUser.getFaculty()
                            || camp.getUserGroup() == "Whole NTU")
                        availableCamps.add(camp);
                }
                for (Camp camp : availableCamps) {
                    System.out.println(camp.getCampInfo().getCampName());
                }
            }
        }));

        addItem(new ActionableItem("Register for a Camp", new ItemAction() { // only accessible once user has selected a
                                                                             // Camp from 'View Camps' list
            public void execute() {

            }
        }));

        addItem(new ActionableItem("Submit Enquiry", new ItemAction() { // only accessible once user has selected a Camp
                                                                        // from 'View Camps' or 'View Registered Camps'
                                                                        // list
            public void execute() {

            }
        }));

        addItem(new ActionableItem("View Enquiries", new ItemAction() {
            public void execute() {

            }
        }));

        addItem(new ActionableItem("Edit Enquiry", new ItemAction() { // only accessible once user has selected an
                                                                      // Enquiry from 'View Enquiries' list
            public void execute() {

            }
        }));

        addItem(new ActionableItem("View Enquiry Reply", new ItemAction() { // only accessible once user has selected an
                                                                            // Enquiry from 'View Enquiries' list
            public void execute() {

            }
        }));

    }
}
