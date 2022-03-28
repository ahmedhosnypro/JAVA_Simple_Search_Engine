package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> userData = new ArrayList<>();


    public static void main(String[] args) {
        addUserData(args);
        startActivity();
    }

    private static void addUserData(String[] args) {
        if (args[0].equalsIgnoreCase("--data")) {

            try (Scanner fileScanner = new Scanner(new File(args[1]))) {
                while (fileScanner.hasNext()) {
                    userData.add(fileScanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Enter the number of people:");
            int dataNum = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < dataNum; i++) {
                userData.add(scanner.nextLine());
            }
        }
    }

    private static void startActivity() {
        System.out.println("""
                                
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");

        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1 -> findPerson();
            case 2 -> printPersons();
            case 0 -> exit();
            default -> {
                System.out.println("Incorrect option! Try again.");
                startActivity();
            }
        }
    }

    private static void findPerson() {
        System.out.println("Enter a name or email to search all suitable people.");
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
        startActivity();
    }

    private static void printPersons() {
        System.out.println("=== List of people ===");
        userData.forEach(System.out::println);
        startActivity();
    }

    private static void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }
}
