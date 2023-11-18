package cams.view.base;

/**
 * Interface for displaying all user interface elements. A
 * <code>Displayable</code> represents a user interface element which can be
 * displayed to the standard output.
 * <p>
 * This interface is implemented by base classes as generic user interface
 * elements like <code>Menu</code> and <code>Form</code>. Further specified
 * user interface components are implemented by either extension from these base
 * classes or association of these base classes.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public interface Displayable {
    /**
     * Clears the standard output and displays this user interface element to the
     * standard output.
     */
    void display();
}
