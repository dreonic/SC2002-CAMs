package cams.view.components.repliable;

import cams.camp.Camp;
import cams.camp.CampController;
import cams.repliable.Enquiry;
import cams.view.DisplayController;
import cams.view.base.ActionableItem;
import cams.view.base.Alert;
import cams.view.base.CommonElements;
import cams.view.base.ItemAction;
import cams.view.base.SelectionMenu;
import cams.view.components.student.CommitteeViewEnquiryMenu;

import java.util.Scanner;

/**
 * The boundary class displaying menu options a committee member can select
 * once they have selected a specific {@code Enquiry}.
 * 
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CommitteeEnquiryMenu extends SelectionMenu {

    /**
     * Constructs the Committee Enquiry Menu specifying the scanner to be used and a {@code Enquiry} to be replied. 
     * 
     * @param scanner
     * @param enquiry
     */
    public CommitteeEnquiryMenu(Scanner scanner, Enquiry enquiry) {
        super(scanner);
        DisplayController displayController = DisplayController.getInstance();
        CampController campController = CampController.getInstance();
        Camp camp = campController.getCurrentCamp();

        setPrompt(CommonElements.getStatusBar("Enquiry Menu") + "\n" + "\"" + enquiry.getQuestion() + "\"" + "\n" + "Camp: " + camp.getCampInfo().getCampName() + "\n");

        addItem(new ActionableItem("Reply", new ItemAction() {
            public void execute() {
                if(!enquiry.getReply().isBlank()) {
                    displayController.setNextDisplay(new Alert("Enquiry has already been answered!", new CommitteeViewEnquiryMenu(scanner), scanner));
                }
                else {
                    displayController.setNextDisplay(new ReplyEnquiryForm(scanner, enquiry));
                }
            }
        }));

        addItem(new ActionableItem("Back", new ItemAction() {
            public void execute() {
                displayController.setNextDisplay(new CommitteeViewEnquiryMenu(scanner));
            }
        }));
    }
}
