package cams;

import cams.camp.CampController;
import cams.serializer.RepliableSerializer;
import cams.user.AuthController;
import cams.user.UserController;
import cams.view.DisplayController;
import cams.view.components.WelcomeMenu;

import java.util.Scanner;

/**
 * The CAMs application's entry point.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class App {
    /**
     * Starts and runs the CAMs application.
     *
     * @param args command line arguments. Unused for this application.
     */
    public static void main(String[] args) {
        startControllersAndDeserialize();
        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();
        stopControllersAndSerialize();
    }

    /**
     * Starts all necessary control classes to deserialize existing user (student and staff), camp,
     * enquiries and suggestions data.
     */
    public static void startControllersAndDeserialize() {
        AuthController.getInstance();
        UserController.getInstance();
        CampController.getInstance();
        RepliableSerializer.deserialize("enquiry", "enquiry_list.xlsx", "suggestion_list.xlsx");
        RepliableSerializer.deserialize("suggestion", "enquiry_list.xlsx", "suggestion_list.xlsx");
    }

    /**
     * Serializes current application state (user table, camp table, etc.) to Excel files and closes
     * control classes.
     */
    public static void stopControllersAndSerialize() {
        RepliableSerializer.serialize("suggestion", "enquiry_list.xlsx", "suggestion_list.xlsx");
        RepliableSerializer.serialize("enquiry", "enquiry_list.xlsx", "suggestion_list.xlsx");
        CampController.close();
        UserController.close();
        AuthController.close();
    }
}
