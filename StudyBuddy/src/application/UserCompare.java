package application;

import java.util.Comparator;

public class UserCompare implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        // Compare by the count of matching preferences (higher count first)
        return Integer.compare(o2.getMatchCount(), o1.getMatchCount());
    }
}