package cams.repliable;

import cams.camp.Camp;
import cams.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code SuggestionEditor} edits the suggestions listed in a specific camp.
 * It implements the basic functionalities of {@code RepliableEditorInterface}.
 *
 * @author Gillbert Susilo Wong
 * @author Juan Frederick
 * @author Karl Devlin Chau
 * @author Pascalis Pandey
 * @author Trang Nguyen
 * @version 1.0
 * @since 2023-11-09
 */
public class SuggestionEditor implements RepliableEditorInterface {
    /**
     * The camp assoiated with the repliable editor
     */
    private final Camp camp;

    /**
     * Constructs a new suggestion editor.
     *
     * @param camp the camp where the suggestions are eidted from.
     */
    public SuggestionEditor(Camp camp) {
        this.camp = camp;
    }

    /**
     * Creates a new {@codeSuggestion} for the specified camp associated with the editor.
     *
     * @param content the content of the suggestion being created.
     * @param student the student that created the suggestion.
     */
    @Override
    public Repliable create(String content, Student student) {
        Suggestion suggestion = new Suggestion(content, student);
        camp.addSuggestion(suggestion);
        return suggestion;
    }

    /**
     * Edits an {@code Suggestion} with new content.
     *
     * @param repliable the suggestion being edited.
     * @param newContent the new content to replace the old content of the suggestion.
     */
    @Override
    public void edit(Repliable repliable, String newContent) {
        Suggestion suggestion = (Suggestion) repliable;
        if (!suggestion.getIsApproved()) {
            suggestion.setContent(newContent);
        }
    }

     /**
     * Deletes an {@code Suggestion}.
     *
     * @param repliable the suggestion being deleted.
     */
    @Override
    public void delete(Repliable repliable) {
        Suggestion suggestion = (Suggestion) repliable;
        camp.removeSuggestion(suggestion);
    }

    /**
     * Approves a {@code Suggestion}.
     *
     * @param repliable the suggestion being approved.
     * @param replyMessage placeholder object to satisfy the interface.
     */
    @Override
    public void reply(Repliable repliable, Object replyMessage) {
        Suggestion suggestion = (Suggestion) repliable;
        if (!suggestion.getIsApproved()) {
            suggestion.setIsApproved(true);
        }
    }

    /**
     * Returns all the {@code Suggestion} in the specified camp.
     * 
     *  @return list of all suggestions in the camp.
     */
    @Override
    public List<Repliable> view() {
        return new ArrayList<>(camp.getSuggestions());
    }
}
