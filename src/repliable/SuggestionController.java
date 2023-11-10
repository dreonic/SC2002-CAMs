package repliable;

import java.util.ArrayList;

import camp.Camp;
import domain.Student;

public class SuggestionController implements RepliableControllerInterface {
    private Camp camp;

    public SuggestionController(Camp camp) {
        this.camp = camp;
    }

    @Override
    public void create(String content, Student student) {
        Suggestion suggestion = new Suggestion(content, student);
        camp.addSuggestion(suggestion);
    }

    @Override
    public void edit(Repliable repliable, String newContent) {
        Suggestion suggestion = (Suggestion) repliable;
        if(suggestion.getIsApproved() == false) {
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
        if(camp.getSuggestions().contains(suggestion)) {
            suggestion.setIsApproved(true);
        }
    }

    @Override
    public ArrayList<Repliable> view() {
        ArrayList<Repliable> suggestionList = new ArrayList<Repliable>();
        for(Suggestion suggestion:camp.getSuggestions()) {
            suggestionList.add(suggestion);
        }
        return suggestionList;
    }
}
