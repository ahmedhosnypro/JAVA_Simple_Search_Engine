// You can experiment here, it won’t be checked

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "Bob Yeh bobyeah@gmail.com";
        String word = sc.nextLine();
        String regex = "(?i)\\b" + word + "\\b";
        //Creating a pattern object
        Pattern pattern = Pattern.compile(regex);
        //Matching the compiled pattern in the String
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println("Number of occurrences of the word test : " + count);
    }
}


