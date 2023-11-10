package repliable;

import camp.Camp;
import domain.Student;

public class EnquiryController implements RepliableControllerInterface {
    private Camp camp;

    public EnquiryController(Camp camp) {
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
    }
}
