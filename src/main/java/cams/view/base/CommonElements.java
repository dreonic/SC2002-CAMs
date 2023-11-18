package cams.view.base;

public class CommonElements {
    public static final String logo = """
                 ██████╗ █████╗ ███╗   ███╗███████╗
                ██╔════╝██╔══██╗████╗ ████║██╔════╝
                ██║     ███████║██╔████╔██║███████╗
                ██║     ██╔══██║██║╚██╔╝██║╚════██║
                ╚██████╗██║  ██║██║ ╚═╝ ██║███████║
                 ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
            """;

    public static final String separator = String.format("%045d", 0).replace("0", "═") + "\n";

    public static String getHeader() {
        return separator + "\n" + logo + "\n" + separator + "\n";
    }

    public static String getHeader(String status) {
        return separator + "\n" + logo + "\n" +
                "═══ " + status + " " +
                "═".repeat(Math.max(0, 40 - status.length())) +
                "\n";
    }

    public static String getStatusBar(String status) {
        return "═══ " + status + " " +
                "═".repeat(Math.max(0, 40 - status.length())) +
                "\n";
    }

    public static void clearSystemOut() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
