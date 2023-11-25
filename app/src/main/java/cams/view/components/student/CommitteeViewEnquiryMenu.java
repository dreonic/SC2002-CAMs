package cams.view.components.student;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.view.CommonElements;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.repliable.CommitteeEnquiryMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The boundary class responsible for displaying a list of enquiries for a committee member to select.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CommitteeViewEnquiryMenu extends SelectionMenu {
    /**
     * Constructs the Committee View Enquiry Menu specifying the scanner to be used.
     * 
     * @param scanner scanner for this menu
     */
    public CommitteeViewEnquiryMenu(Scanner scanner) {
        super(scanner);
        CampController campController = CampController.getInstance();
        DisplayController displayController = DisplayController.getInstance();
        Camp camp = campController.getCurrentCamp();
        List<Enquiry> enquiriesList = new ArrayList<Enquiry>(camp.getEnquiries());

        setPrompt(CommonElements.getStatusBar("View Enquiries"));

        for (Enquiry enquiry : enquiriesList) {
            addItem(new ActionableItem(enquiry.getQuestion(), new ItemAction() {
                public void execute() {
                    displayController.setNextDisplay(new CommitteeEnquiryMenu(scanner, enquiry));
                }
            }));
        }

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new StudentCommitteeMenu(scanner));
            }
        }));
    }
}
