package view;

public class MenuItem {
    private String content;
    private MenuItemAction action;

    public MenuItem(String content, MenuItemAction action) {
        this.content = content;
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void runAction() {
        action.execute();
    }
}
