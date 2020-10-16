package ghFalsePositives;

import java.util.List;

public class Issue1155 {
    public static void require(boolean condition, String error) {
        if (!condition) {
            throw new IllegalStateException(error);
        }
    }

    public int demoOfInvalidNPEBeingReported(List<String> theList, String findValue) {
        String found = null;
        for (String value : theList) {
            if (value.contains(findValue)) {
                found = value;
                break;
            }
        }
        require(found != null, "Should have found it");
        return found.length();
    }
}
