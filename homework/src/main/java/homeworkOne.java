import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;


public class homeworkOne {

    public static void main(String argus[]) {
        //Store borrowIds
        HashSet<String> borrowerIds = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            String borrowerHead = "u";
            borrowerHead += i + 1;
            borrowerIds.add(borrowerHead);
        }
        //Store bookIds
        HashSet<String> bookIds = new HashSet<>();
        for (int j = 0; j < 10; j++) {
            String bookHead = "b";
            bookHead += j + 1;
            bookIds.add(bookHead);
        }
        //Store checked books
        TreeMap<String, String> records = new TreeMap<>();

        //Start application
        String command = "";
        while (!command.equals("Exit")) {
            System.out.println("Please input operation type:(Checkout/Return/Exit)");
            Scanner scanner = new Scanner(System.in);
            command = scanner.next();
            String borrowerId;
            String bookId;
            if (command.equals("Checkout")) {
                System.out.println("Please input Borrower ID:");
                Scanner scanner11 = new Scanner(System.in);
                borrowerId = scanner11.next();
                if (borrowerIds.contains(borrowerId)) {
                    System.out.println("Please input Book ID:");
                    Scanner scanner12 = new Scanner(System.in);
                    bookId = scanner12.next();
                    if (bookIds.contains(bookId)) {
                        if (!records.containsKey(bookId)) {
                            records.put(bookId, borrowerId);
                            System.out.println("Checkout successful. \n");
                        } else {
                            System.out.println("The book is already checked out. \n");
                        }
                    } else {
                        System.out.printf("There's no such book: %s \n", bookId);
                    }
                } else {
                    System.out.printf("%s is not a valid user. \n", borrowerId);
                }
            } else if (command.equals("Return")) {
                System.out.println("Please input Borrower ID:");
                Scanner scanner21 = new Scanner(System.in);
                borrowerId = scanner21.next();
                if (borrowerIds.contains(borrowerId)) {
                    System.out.println("Please input Book ID:");
                    Scanner scanner22 = new Scanner(System.in);
                    bookId = scanner22.next();
                    if (bookIds.contains(bookId)) {
                        if (records.containsKey(bookId)) {
                            if (records.get(bookId).equals(borrowerId)) {
                                records.remove(bookId, borrowerId);
                                System.out.println("Return successful. \n");
                            } else {
                                System.out.printf("Operation failed: the book was checked out by someone else. \n");
                            }
                        } else {
                            System.out.println("The book is not checked out. \n");
                        }
                    } else {
                        System.out.printf("There's no such book: %s \n", bookId);
                    }
                } else {
                    System.out.printf("%s is not a valid user. \n", borrowerId);
                }
            } else if (command.equals("Exit")) {
                System.exit(0);
            } else {
                System.out.println("There's no such operation. \n");
            }
        }
    }
}
