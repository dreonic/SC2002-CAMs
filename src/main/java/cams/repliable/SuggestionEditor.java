package cams.repliable;

import cams.camp.Camp;
import cams.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class SuggestionEditor implements RepliableEditorInterface {
    private final Camp camp;

    public SuggestionEditor(Camp camp) {
        this.camp = camp;
    }

    @Override
    public Repliable create(String content, Student student) {
        Suggestion suggestion = new Suggestion(content, student);
        camp.addSuggestion(suggestion);
        return suggestion;
    }

    @Override
    public void edit(Repliable repliable, String newContent) {
        Suggestion suggestion = (Suggestion) repliable;
        if (!suggestion.getIsApproved()) {
            suggestion.setContent(newContent);
        }
    }

    @Override
    public void delete(Repliable repliable) {
        Suggestion suggestion = (Suggestion) repliable;
        camp.removeSuggestion(suggestion);
    }

    @Override
    public void reply(Repliable repliable, Object replyMessage) {
        Suggestion suggestion = (Suggestion) repliable;
        if (!suggestion.getIsApproved()) {
            suggestion.setIsApproved(true);
        }
    }

    @Override
    public List<Repliable> view() {
        return new ArrayList<>(camp.getSuggestions());
    }
}
