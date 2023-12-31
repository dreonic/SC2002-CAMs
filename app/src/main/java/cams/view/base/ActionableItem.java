package cams.view.base;

/**
 * Base class for user interface elements with distinct actions. This base class
 * provides other classes, mainly {@link SelectionMenu}, to display choices
 * each with their own content and action.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class ActionableItem {
    private final ItemAction action;
    private String content;

    /**
     * Constructs an item with content and action.
     *
     * @param content item content
     * @param action  item action
     */
    public ActionableItem(String content, ItemAction action) {
        this.content = content;
        this.action = action;
    }

    /**
     * Retrieves item content.
     *
     * @return item content
     */
    public String getContent() {
        return content;
    }

    /**
     * Assigns item content.
     *
     * @param content item content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Executes item action.
     */
    public void runAction() {
        action.execute();
    }
}
