package cams.view.base;

/**
 * Utility class providing common text elements used by classes of the {@code view} package.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-23
 */
public class CommonElements {
    /**
     * The CAMs application logo.
     */
    public static final String logo = """
                 ██████╗ █████╗ ███╗   ███╗███████╗
                ██╔════╝██╔══██╗████╗ ████║██╔════╝
                ██║     ███████║██╔████╔██║███████╗
                ██║     ██╔══██║██║╚██╔╝██║╚════██║
                ╚██████╗██║  ██║██║ ╚═╝ ██║███████║
                 ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
            """;

    /**
     * A 45-character wide horizontal line.
     */
    public static final String separator = String.format("%045d", 0).replace("0", "═") + "\n";

    /**
     * Returns the CAMs application header consisting of the CAMs application logo in between two
     * separators. Example:
     *
     * <pre>
     * {@code
     * ═════════════════════════════════════════════
     *
     *       ██████╗ █████╗ ███╗   ███╗███████╗
     *      ██╔════╝██╔══██╗████╗ ████║██╔════╝
     *      ██║     ███████║██╔████╔██║███████╗
     *      ██║     ██╔══██║██║╚██╔╝██║╚════██║
     *      ╚██████╗██║  ██║██║ ╚═╝ ██║███████║
     *       ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
     *
     * ═════════════════════════════════════════════
     * }
     * </pre>
     *
     * @return the CAMs application header
     */
    public static String getHeader() {
        return separator + "\n" + logo + "\n" + separator;
    }

    /**
     * Returns the CAMs application header consisting of the CAMs application logo in between two
     * separators with a label displayed on the bottom separator. Example:
     *
     * <pre>
     * {@code
     * ═════════════════════════════════════════════
     *
     *       ██████╗ █████╗ ███╗   ███╗███████╗
     *      ██╔════╝██╔══██╗████╗ ████║██╔════╝
     *      ██║     ███████║██╔████╔██║███████╗
     *      ██║     ██╔══██║██║╚██╔╝██║╚════██║
     *      ╚██████╗██║  ██║██║ ╚═╝ ██║███████║
     *       ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
     *
     * ═══ Status ══════════════════════════════════
     * }
     * </pre>
     *
     * @param status label to be displayed
     * @return the CAMs application header with the label
     */
    public static String getHeader(String status) {
        return separator + "\n" + logo + "\n" + getStatusBar(status);
    }

    /**
     * Returns a separator with a label displayed on it. Example:
     *
     * <pre>
     * {@code
     * ═══ Status ══════════════════════════════════
     * }
     * </pre>
     *
     * @param status label to be displayed
     * @return separator with label
     */
    public static String getStatusBar(String status) {
        return "═══ " + status + " " +
                "═".repeat(Math.max(0, 40 - status.length())) +
                "\n";
    }

    /**
     * Clears the standard output; usually used before displaying a new {@code Displayable } user
     * interface element.
     */
    public static void clearSystemOut() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
