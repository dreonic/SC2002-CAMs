import java.util.Scanner;

import view.DisplayController;
import view.WelcomeMenu;

public class CAMs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DisplayController menuController = DisplayController.getInstance(new WelcomeMenu(sc));
        menuController.displayCurrent();
    }
}
