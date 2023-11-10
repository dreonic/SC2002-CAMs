package cams.repliable;

import cams.camp.Camp;
import cams.domain.Student;

public class SuggestionController implements RepliableControllerInterface {
    private Camp camp;

    public SuggestionController(Camp camp) {
        this.camp = camp;
    }

    @Override
    public void create(String content, Student student) {
        // TODO
    }

    @Override
    public void edit(Repliable repliable, String newContent) {
        // TODO
    }

    @Override
    public void delete(Repliable repliable) {
        // TODO
    }

    @Override
    public void reply(Repliable repliable, Object replyMessage) {
        // TODO
    }

    @Override
    public Repliable[] view() {
        // TODO
        return new Suggestion[1];
    }
}
