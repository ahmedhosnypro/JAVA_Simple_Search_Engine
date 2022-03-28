package search;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AllMatching implements SearchingMethod {
    @Override
    public void search(ArrayList<String> userData, ArrayList<String> words) {
        var lines = userData.stream();
        for (var word : words) {
            lines = filterByWord(lines, word);
        }
        lines.forEach(System.out::println);
    }

    Stream<String> filterByWord(Stream<String> lines, String word) {
        Pattern pattern = Pattern.compile("(?i)\\b" + word + "\\b");
        return lines.filter(line -> pattern.matcher(line).find());
    }
}