package view;

public class MenuController {
    private static MenuController menuController;
    private Menu currentMenu;

    private MenuController() {
    }

    public static MenuController getInstance() {
        if (menuController == null) {
            menuController = new MenuController();
        }
        return menuController;
    }

    public void displayCurrentMenu() {
        currentMenu.display();
    }

    public void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }
}
