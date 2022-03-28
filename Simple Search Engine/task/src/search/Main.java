package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Searcher searcher = new Searcher();
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
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        ArrayList<String> toSearch = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toCollection(ArrayList::new));

        switch (strategy) {
            case "ALL" -> searcher.setMethod(new AllMatching());
            case "ANY" -> searcher.setMethod(new AnyMatching());
            case "NONE" -> searcher.setMethod(new NoneMatching());
            default -> throw new IllegalArgumentException();
        }

        searcher.search(userData, toSearch);

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
