import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BeautySalon salon = new BeautySalon(); // экземпляр класса бьюти салон
        Scanner scanner = new Scanner(System.in); // объект сканер для инпута
        int choice;

        do {
            System.out.println("Hello! You have the following available functions:");
            System.out.println("1) Show beauty procedures list");
            System.out.println("2) Add a beauty procedure");
            System.out.println("3) Add a new user");
            System.out.println("4) Book a beauty procedure");
            System.out.println("5) Cancel booking for a beauty procedure");
            System.out.println("0) Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    salon.displayProcedures();
                    break;
                case 2:
                    salon.addProcedure(scanner);
                    break;
                case 3:
                    salon.addUser(scanner);
                    break;
                case 4:
                    salon.bookProcedure(scanner);
                    break;
                case 5:
                    salon.cancelBooking(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}