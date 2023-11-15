package cams;

import java.util.Scanner;

import cams.camp.CampController;
import cams.serializer.RepliableSerializer;
import cams.user.AuthController;
import cams.user.UserController;
import cams.view.DisplayController;
import cams.view.components.WelcomeMenu;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();
        startControllersAndDeserialize();
    }

    public static void startControllersAndDeserialize() {
        AuthController.getInstance();
        UserController.getInstance();
        CampController.getInstance();
        RepliableSerializer.deserialize("enquiry");
        RepliableSerializer.deserialize("suggestion");
    }

    public static void stopControllersAndSerialize() {
        AuthController.close();
        UserController.close();
        CampController.close();
        RepliableSerializer.serialize("enquiry");
        RepliableSerializer.serialize("suggestion");
    }
}
