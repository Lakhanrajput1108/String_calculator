package stringcalculator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberParser {

    private String delimiter;

    public NumberParser() {
        delimiter = "[,\n]";
    }

    private String[] splitOnDelimiter(String numbers) {
        if (hasCustomDelimiter(numbers)) {
            return numbers
                    .substring(numbers.indexOf("\n") + 1)
                    .split(extractDelimiter(numbers));
        }
        return numbers.split(delimiter);
    }

    private boolean hasCustomDelimiter(String numbers) {
        return numbers.startsWith("/");
    }

    List<Integer> fromStringToNumber(String numbers) {
        return Arrays
                .stream(splitOnDelimiter(numbers))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private String extractDelimiter(String input) {
        String escapedInput;
        int START_OF_SUBSTRING = input.indexOf("\n");

        escapedInput = input
                .substring(0, START_OF_SUBSTRING)
                .replace("//", "")
                .replace("[", "")
                .replace("]", "");
        return "\\Q" + escapedInput + "\\E";
    }
}