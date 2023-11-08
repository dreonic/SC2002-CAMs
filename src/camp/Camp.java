package camp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Camp {
    private CampInfo campInfo;
    private CampDate campDate;
    // private Staff staffInCharge;
    private String userGroup;
    // private Set<Enquiry> enquiries;
    // private Set<Suggestion> suggestions;
    // private Set<Student> attendees;
    // private HashMap<Student, Integer> committee;

    public Camp(String campName, String location, String description, LocalDate startDate, LocalDate endDate,
            LocalDate registrationDeadline, int totalSlots, boolean isVisible, String userGroup) {
        this.campInfo = new CampInfo(campName, location, description, totalSlots, 10, true); 
        this.campDate = new CampDate(startDate, endDate, registrationDeadline);
        this.userGroup = userGroup;
        // this.enquiries = new HashSet<>();
        // this.suggestions = new HashSet<>();
        // this.attendees = new HashSet<>();
        // this.committee = new HashMap<>();
    }

    // GETTERS
    public CampInfo getCampInfo() {
        return campInfo;
    }

    public CampDate getCampDate() {
        return campDate;
    }

    // public Staff getStaffInCharge() {
    //     return staffInCharge;
    // }

    public String getUserGroup() {
        return userGroup;
    }

    // public Set<Enquiry> getEnquiries() {
    //     return enquiries;
    // }

    // public Set<Suggestion> getSuggestions() {
    //     return suggestions;
    // }

    // public void addEnquiry(Enquiry enquiry) {
    //     enquiries.add(enquiry);
    // }

    // public void removeEnquiry(Enquiry enquiry) {
    //     enquiries.remove(enquiry);
    // }

    // public Enquiry[] getEnquiriesArray() {
    //     return enquiries.toArray(new Enquiry[0]);
    // }

    // public void addSuggestion(Suggestion suggestion) {
    //     suggestions.add(suggestion);
    // }

    // public void removeSuggestion(Suggestion suggestion) {
    //     suggestions.remove(suggestion);
    // }

    // public Suggestion[] getSuggestionsArray() {
    //     return suggestions.toArray(new Suggestion[0]);
    // }
}
