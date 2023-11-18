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
    }

    public static void startControllersAndDeserialize() {
        AuthController.getInstance();
        UserController.getInstance();
        CampController.getInstance();
        RepliableSerializer.deserialize("enquiry");
        RepliableSerializer.deserialize("suggestion");
    }

    public static void stopControllersAndSerialize() {
        RepliableSerializer.serialize("suggestion");
        RepliableSerializer.serialize("enquiry");
        CampController.close();
        UserController.close();
        AuthController.close();
    }
}
