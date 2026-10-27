package glob;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GlobPatternTest {

    @Test
    void questionMarkMatchesAnySingleCharacter() {
        GlobPattern glob = new GlobPattern("?at");
        assertTrue(glob.match("Cat"));
        assertTrue(glob.match("cat"));
        assertTrue(glob.match("bat"));
    }

    @Test
    void shorterTextDoesNotMatch() {
        assertFalse(new GlobPattern("?at").match("at"));
    }

    @Test
    void longerTextDoesNotMatch() {
        assertFalse(new GlobPattern("?at").match("boat"));
        assertFalse(new GlobPattern("?at").match("cats"));
    }

    @Test
    void literalCharactersMustMatchExactly() {
        GlobPattern glob = new GlobPattern("abc");
        assertTrue(glob.match("abc"));
        assertFalse(glob.match("abd"));
        assertFalse(glob.match("Abc"));
    }

    @Test
    void literalMismatchAfterQuestionMark() {
        assertFalse(new GlobPattern("?at").match("Cot"));
    }

    @Test
    void onlyQuestionMarks() {
        GlobPattern glob = new GlobPattern("???");
        assertTrue(glob.match("abc"));
        assertTrue(glob.match("???"));
        assertFalse(glob.match("ab"));
    }

    @Test
    void questionMarkInTextIsJustACharacter() {
        assertFalse(new GlobPattern("abc").match("a?c"));
    }

    @Test
    void emptyPatternMatchesOnlyEmptyText() {
        GlobPattern glob = new GlobPattern("");
        assertTrue(glob.match(""));
        assertFalse(glob.match("a"));
    }

    @Test
    void nullTextDoesNotMatch() {
        assertFalse(new GlobPattern("?at").match(null));
    }
}
