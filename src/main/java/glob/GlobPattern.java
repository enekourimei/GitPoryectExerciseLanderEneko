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
        // TODO: not implemented yet
        return false;
    }
}
