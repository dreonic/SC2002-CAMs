package cams.repliable;

import cams.domain.Student;

import java.util.List;

public interface RepliableEditorInterface {
    Repliable create(String content, Student student);

    void edit(Repliable repliable, String newContent);

    void delete(Repliable repliable);

    void reply(Repliable repliable, Object replyMessage);

    List<Repliable> view();
}
