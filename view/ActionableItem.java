package view;

public class ActionableItem {
    private String content;
    private ItemAction action;

    public ActionableItem(String content, ItemAction action) {
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
