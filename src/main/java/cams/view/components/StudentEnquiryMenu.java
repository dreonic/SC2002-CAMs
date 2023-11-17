package cams.view.components;

import java.util.Map;
import java.util.Scanner;

import cams.camp.CampController;
import cams.domain.StudentController;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.base.TextBox;

public class StudentEnquiryMenu extends SelectionMenu {
    public StudentEnquiryMenu(Scanner scanner) {
        super(scanner);
        setTitle("View Enquiry:\n");

        addInput(new TextBox("New Enquiry", scanner));

        setAction(new ItemAction() {
            public void execute() {
                StudentController studentController = StudentController.getInstance();
                DisplayController displayController = DisplayController.getInstance();
                CampController campController = CampController.getInstance();

                EnquiryEditor enquiryEditor = new EnquiryEditor(campController.getCurrentCamp());

                Map<String, String> values = getValues();

                

                enquiryEditor.create(
                        values.get("Question"),
                        studentController.getCurrentStudent());

                displayController.setNextDisplay(new StudentMenu(scanner));
            }
        });
    }
}
