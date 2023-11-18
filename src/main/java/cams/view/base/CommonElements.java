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
        StringBuilder str = new StringBuilder(separator + "\n");
        str.append(logo + "\n");
        str.append(separator);
        return str.toString();
    }

    public static String getHeader(String status) {
        StringBuilder str = new StringBuilder(separator + "\n");
        str.append(logo + "\n");
        str.append("═══ " + status + " ");
        for (int i = 0; i < 40 - status.length(); i++)
            str.append("═");
        return str.toString();
    }

    public static String getStatusBar(String status) {
        StringBuilder str = new StringBuilder();
        str.append("═══ " + status + " ");
        for (int i = 0; i < 40 - status.length(); i++)
            str.append("═");
        return str.toString();
    }

    public static void clearSystemOut() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
    }
}
