package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<String> userData = new ArrayList<>();
    private static final HashMap<String, TreeSet<Integer>> invertedIndex = new HashMap<>();

    public static void main(String[] args) {
        addUserData(args);
        createInvertedIndex();
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

    private static void createInvertedIndex() {
        int lineNum = 0;
        for (var data : userData) {
            var line = data.split(" ");
            for (var word : line) {
                if (invertedIndex.containsKey(word)) {
                    invertedIndex.get(word).add(lineNum);
                } else {
                    invertedIndex.put(word, new TreeSet<>(Set.of(lineNum)));
                }
            }
            lineNum++;
        }
    }

    private static void startActivity() {
        System.out.println("""
                                
                === Menu ===
                1. Find a person
                2. Print all people
                0. Exit""");

        int option = Integer.parseInt(scanner.nextLine());
        System.out.println();
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
        String toSearch = scanner.nextLine();

        if (!invertedIndex.containsKey(toSearch)) {
            System.out.println("No matching people found.");
            System.out.println();
        } else {
            for (Integer index : invertedIndex.get(toSearch)) {
                System.out.println(userData.get(index));
            }
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
