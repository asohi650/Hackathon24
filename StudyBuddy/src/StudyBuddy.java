import java.util.List;

public class StudyBuddy {
    private String major;
    private int year;
    private List<Integer> preferences;
    private String meetingPreference;

    public StudyBuddy(String major, int year, List<Integer> preferences, String meetingPreference) {
        this.major = major;
        this.year = year;
        this.preferences = preferences;
        this.meetingPreference = meetingPreference;
    }

    // Getters for matching
    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public List<Integer> getPreferences() {
        return preferences;
    }

    public String getMeetingPreference() {
        return meetingPreference;
    }

    // Method to check similarity with another StudyBuddy
    public boolean matches(StudyBuddy other) {
        if (!this.major.equals(other.major) || this.year != other.year || !this.meetingPreference.equals(other.meetingPreference)) {
            return false;
        }

        // Check if there is at least one matching preference
        for (Integer preference : this.preferences) {
            if (other.preferences.contains(preference)) {
                return true; // Found a matching preference
            }
        }
        return false; // No matching preference found
    }
}