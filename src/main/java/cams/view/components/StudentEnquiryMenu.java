package cams.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.domain.Student;
import cams.domain.StudentController;
import cams.repliable.Enquiry;
import cams.repliable.EnquiryEditor;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.base.TextBox;
import scala.annotation.elidable;

public class StudentEnquiryMenu extends SelectionMenu {
    public StudentEnquiryMenu(Scanner scanner, Enquiry currentEnquiry) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();
        EnquiryEditor enquiryEditor = new EnquiryEditor(camp);

        enquiryEditor.setCurrentEnquiry(currentEnquiry);
        
        System.out.println(currentEnquiry.getQuestion());

        addItem(new ActionableItem("Edit", new ItemAction() {
            public void execute() {
                
            }
        }));

        addItem(new ActionableItem("Delete", new ItemAction() {
            public void execute() {
                enquiryEditor.delete(currentEnquiry);
                displayController.setNextDisplay(new Alert("Suggestion has been approved", new StaffViewSuggestionMenu(scanner), scanner));
                
            }
        }));

        
        addItem(new ActionableItem("View Reply", new ItemAction() {
            public void execute() {
                
            }
        }));

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
