public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
} 

package stringcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ConcatNumbersByComma {
    static String concatNumbers(List<Integer> negativeNumbers) {
        return Arrays.stream(negativeNumbers.toArray()).map(String::valueOf).collect(Collectors.joining(", "));
    }
}

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


package stringcalculator;

import java.util.ArrayList;
import java.util.List;

import static stringcalculator.ConcatNumbersByComma.concatNumbers;

class StringCalculatorProvider {

    private final NumberParser numberParser = new NumberParser();

    int add(String numbers) {
        if (numbers.isEmpty()) return 0;
        return adding(numbers);
    }

    private int adding(String numbers) {
        List<Integer> numbersSplit = numberParser.fromStringToNumber(numbers);
        checkForNegativeNumbers(numbersSplit);
        return numbersSplit.stream().filter(n -> n <= 1000).reduce(0, (a, b) -> a + b);
    }

    private void checkForNegativeNumbers(List<Integer> numbersSplit) {
        ArrayList<Integer> negativeNumbers = new ArrayList<>();
        for (Integer integer : numbersSplit) {
            if (integer < 0) negativeNumbers.add(integer);
        }
        if (negativeNumbers.size() > 0) throw new NegativesNumbersException("negatives not allowed: " +  concatNumbers(negativeNumbers));

    }

    class NegativesNumbersException extends IllegalArgumentException {
        NegativesNumbersException(String message) {
            super(message);
        }
    }

}