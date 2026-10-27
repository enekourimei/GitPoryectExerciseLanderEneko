package glob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: MainClass [PATTERN]");
            return;
        }

        GlobPattern glob = new GlobPattern(args[0]);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(glob.match(line) ? "1" : "0");
            }
        } catch (IOException e) {
            System.out.println("-1");
        }
    }
}
