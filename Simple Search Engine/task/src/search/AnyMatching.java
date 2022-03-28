package search;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnyMatching implements SearchingMethod {
    @Override
    public void search(ArrayList<String> userData, ArrayList<String> words) {
        for (var line : userData) {
            for (var word : words) {
                Pattern pattern = Pattern.compile("(?i)\\b" + word + "\\b");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(line);
                    break;
                }
            }
        }
    }
}
