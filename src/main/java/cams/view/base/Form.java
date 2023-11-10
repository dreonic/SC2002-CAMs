package cams.view.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Form implements Displayable {
    private String title;
    private List<TextBox> inputs;
    private Scanner scanner;
    private ItemAction action;

    public Form(Scanner scanner) {
        title = "";
        this.scanner = scanner;
        action = null;
        inputs = new ArrayList<TextBox>();
    }

    public Form(String title, Scanner scanner) {
        this.title = title;
        this.scanner = scanner;
        action = null;
        inputs = new ArrayList<TextBox>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAction(ItemAction action) {
        this.action = action;
    }

    public void addInput(TextBox input) {
        inputs.add(input);
    }

    public Map<String, String> getValues() {
        Map<String, String> res = new HashMap<String, String>();
        for (TextBox tb : inputs)
            res.put(tb.getLabel(), tb.getValue());
        return res;
    }

    public void display() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(getTitle());
        for (int i = 0; i < inputs.size(); i++)
            inputs.get(i).display();
        action.execute();
    }
}
