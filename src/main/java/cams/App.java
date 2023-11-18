package cams;

import cams.camp.CampController;
import cams.serializer.RepliableSerializer;
import cams.user.AuthController;
import cams.user.UserController;
import cams.view.DisplayController;
import cams.view.components.WelcomeMenu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        startControllersAndDeserialize();
        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();
        stopControllersAndSerialize();
    }

    public static void startControllersAndDeserialize() {
        AuthController.getInstance();
        UserController.getInstance();
        CampController.getInstance();
        RepliableSerializer.deserialize("enquiry", "enquiry_list.xlsx", "suggestion_list.xlsx");
        RepliableSerializer.deserialize("suggestion", "enquiry_list.xlsx", "suggestion_list.xlsx");
    }

    public static void stopControllersAndSerialize() {
        RepliableSerializer.serialize("suggestion", "enquiry_list.xlsx", "suggestion_list.xlsx");
        RepliableSerializer.serialize("enquiry", "enquiry_list.xlsx", "suggestion_list.xlsx");
        CampController.close();
        UserController.close();
        AuthController.close();
    }
}
