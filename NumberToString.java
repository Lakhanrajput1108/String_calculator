package stringcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ConcatNumbersByComma {
    static String concatNumbers(List<Integer> negativeNumbers) {
        return Arrays.stream(negativeNumbers.toArray()).map(String::valueOf).collect(Collectors.joining(", "));
    }
}