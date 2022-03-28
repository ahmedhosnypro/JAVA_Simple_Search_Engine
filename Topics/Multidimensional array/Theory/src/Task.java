// You can experiment here, it won’t be checked

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    public static void main(String[] args) {
        String s = "Regenia EnderleLeopold";
        Pattern pattern = Pattern.compile("Leopold", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find())
            System.out.println(matcher.start());
    }
}
