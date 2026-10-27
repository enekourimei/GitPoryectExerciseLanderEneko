package glob;

/**
 * Simple glob pattern: '?' matches any single character,
 * any other character must match exactly.
 */
public class GlobPattern {

    private final String pattern;

    public GlobPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean match(String text) {
        if (text == null) {
            return false;
        }

        if (text.length() != pattern.length()) {
            return false;
        }

        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            if (p != '?' && p != text.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
