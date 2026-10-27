package glob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainClassTest {

    private static final String NL = System.lineSeparator();
    private static final String USAGE = "Usage: MainClass [PATTERN]" + NL;

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream out;

    @BeforeEach
    void captureOutput() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }

    private String output() {
        return out.toString(StandardCharsets.UTF_8);
    }

    @Test
    void singleMatchingLine() {
        setInput("Cat\n");
        MainClass.main(new String[] {"?at"});
        assertEquals("1" + NL, output());
    }

    @Test
    void severalLines() {
        setInput("cat\nbat\npattern\nat\n");
        MainClass.main(new String[] {"?at"});
        assertEquals("1" + NL + "1" + NL + "0" + NL + "0" + NL, output());
    }

    @Test
    void lastLineWithoutNewline() {
        setInput("cat\nboat");
        MainClass.main(new String[] {"?at"});
        assertEquals("1" + NL + "0" + NL, output());
    }

    @Test
    void emptyInputPrintsNothing() {
        setInput("");
        MainClass.main(new String[] {"?at"});
        assertEquals("", output());
    }

    @Test
    void noArgumentsPrintsUsage() {
        setInput("cat\n");
        MainClass.main(new String[] {});
        assertEquals(USAGE, output());
    }

    @Test
    void tooManyArgumentsPrintsUsage() {
        setInput("cat\n");
        MainClass.main(new String[] {"?at", "extra"});
        assertEquals(USAGE, output());
    }

    @Test
    void readErrorPrintsNegativeNumber() {
        System.setIn(new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("simulated read error");
            }
        });
        MainClass.main(new String[] {"?at"});
        assertEquals("-1" + NL, output());
    }

    @Test
    void canBeInstantiated() {
        // Covers the implicit default constructor for 100% coverage
        new MainClass();
    }
}
