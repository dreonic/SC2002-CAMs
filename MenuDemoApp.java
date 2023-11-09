import java.util.Scanner;

import view.MenuController;
import view.WelcomeMenu;

public class MenuDemoApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuController menuController = MenuController.getInstance();
        menuController.setCurrentMenu(new WelcomeMenu(sc));
        menuController.displayCurrentMenu();
    }
}
