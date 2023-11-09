package view;

import java.util.Scanner;

public class InvalidAlert extends Alert {
    public InvalidAlert(Displayable previousDisplayable, Scanner scanner) {
        super("Invalid choice!", previousDisplayable, scanner);
    }
}
