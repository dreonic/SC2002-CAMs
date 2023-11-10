package repliable;

import java.util.ArrayList;

import domain.Student;

public interface RepliableControllerInterface {
    public void create(String content, Student student);

    public void edit(Repliable repliable, String newContent);

    public void delete(Repliable repliable);

    public void reply(Repliable repliable, Object replyMessage);

    public ArrayList<Repliable> view();
}
