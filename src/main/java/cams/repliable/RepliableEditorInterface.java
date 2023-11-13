package cams.repliable;

import java.util.ArrayList;

import cams.domain.Student;

public interface RepliableEditorInterface {
    public void create(String content, Student student);

    public void edit(Repliable repliable, String newContent);

    public void delete(Repliable repliable);

    public void reply(Repliable repliable, Object replyMessage);

    public ArrayList<Repliable> view();
}