package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> userData = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Enter the number of people:");
        int dataNum = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < dataNum; i++) {
            userData.add(scanner.nextLine());
        }
        System.out.println();

        System.out.println("Enter the number of search queries:");
        int queriesNum = Integer.parseInt(scanner.nextLine());
        System.out.println();

        for (int i = 0; i < queriesNum; i++) {
            System.out.println("Enter data to search people:");
            String query = scanner.nextLine();

            Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
            if (userData.stream().noneMatch(d -> pattern.matcher(d).find())) {
                System.out.println("No matching people found.");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("Found people:");
                userData.stream().filter(d -> pattern.matcher(d).find()).forEach(System.out::println);
            }
            System.out.println();
        }
    }
}
