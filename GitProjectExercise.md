# Exercise

Implement the Java application described in the following section.
You should work in pairs for this exercise.


One developer should focus on the implementation (code under `src/main`), while the other should focus on testing (code under `src/test`).
The developer in charge of the implementation should commit frequently to allow the other developers to adjust their integration with the tests. For example, try to commit the unimplemented interfaces first, then the implemented GlobPattern.match, then the main function, so that the other developer can check the tests.
The developer in charge of the testing can begin implementing the tests based on the specifications here, and then adjust and check the tests when the implementor commits.

Git branching:
* dev: The developer working on the implementation should commit here. You should also merge from master into this branch to get the implementation fixes from the tester
* tests: The developer working on the tests should commit here. You should also merge from dev into this brach to get the latest implementation code
* master: You should merge from tests into here when some part of the exercise has been implemented and tested (passing tests)


# Application

Implement a tool in Java to match input text lines with simple patterns.

The following patterns must be supported:
* "?" matches any single character
* Any other character must match exactly

For example:
* "?at" Matches	"Cat", "cat", or "bat", but not "at" or "boat"

## Main

The tool will receive exactly one command line argument, which will be the pattern to match.
The tool will read individual lines from STDIN, and write a match result to STDOUT for each line:
* "0" if the text does not match the pattern
* "1" if the text matches the pattern
* Some negative number to indicate errors, if there can be any

If the tool receives any number of command line arguments other than 1, it should just print the following message and exit:
"Usage: $(COMMAND) [PATTERN]"

Example;
```
echo "Cat" | $(COMMAND) "?at"
1
```

Example;
```
$(COMMAND) "?at"
cat
1
bat
1
pattern
0
```

## Interfaces

You should create a `GlobPattern` class for each pattern to match.

This class should implement the following method:
```java
class GlobPattern {
    boolean match(String text);
}
```

## Testing

Implement the tests for:
* The main code (STDIO interface, test by calling MainClass.main and using System.setIn/System.setOut)
* The GlobPattern.match method

You should aim for 100% statement + branch coverage
