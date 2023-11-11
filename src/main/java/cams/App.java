package cams;

import java.util.Scanner;
import cams.view.DisplayController;
import cams.view.components.WelcomeMenu;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();
    }
}
