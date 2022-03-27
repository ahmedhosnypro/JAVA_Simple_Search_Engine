package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<String> input = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toCollection(ArrayList::new));
        String word = scanner.nextLine();

        if (input.contains(word)) {
            System.out.println(input.indexOf(word) + 1);
        } else {
            System.out.println("Not found");
        }
    }
}
