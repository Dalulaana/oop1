import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = new DatabaseConnection().connect()) {
            BeautySalon salon = new BeautySalon();
            salon.loadProceduresFromDatabase(conn);
            salon.usersFromDatabase(conn);
            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("Hello! You have the following available functions:");
                System.out.println("1) Show beauty procedures list");
                System.out.println("2) Add a beauty procedure");
                System.out.println("3) Update a beauty procedure");
                System.out.println("4) Delete a beauty procedure");
                System.out.println("5) Add a new user");
                System.out.println("6) Book a beauty procedure");
                System.out.println("7) Cancel booking for a beauty procedure");
                System.out.println("0) Exit");
                System.out.print("Enter your choice: ");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        salon.displayProcedures(conn);
                        break;
                    case 2:
                        salon.addProcedure(conn, scanner);
                        break;
                    case 3:
                        salon.updateProcedure(conn, scanner);
                        break;
                    case 4:
                        salon.deleteProcedure(conn, scanner);
                        break;
                    case 5:
//                        salon.addUser(scanner);
                        try {
                            salon.addUser(conn, scanner);
                        } catch (SQLException e) {
                            System.out.println("Error occurred while adding user: " + e.getMessage());
                        }
                        break;
                    case 6:
                        salon.bookProcedure(conn, scanner);
                        break;
                    case 7:
                        salon.cancelBooking1(conn, scanner);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);

            scanner.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
//        BeautySalon salon = new BeautySalon(); // экземпляр класса бьюти салон
//        Scanner scanner = new Scanner(System.in); // объект сканер для инпута
//        int choice;
//
//        do {
//            System.out.println("Hello! You have the following available functions:");
//            System.out.println("1) Show beauty procedures list");
//            System.out.println("2) Add a beauty procedure");
//            System.out.println("3) Add a new user");
//            System.out.println("4) Book a beauty procedure");
//            System.out.println("5) Cancel booking for a beauty procedure");
//            System.out.println("0) Exit");
//            System.out.print("Enter your choice: ");
//
//            choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    salon.displayProcedures();
//                    break;
//                case 2:
//                    salon.addProcedure(scanner);
//                    break;
//                case 3:
//                    salon.addUser(scanner);
//                    break;
//                case 4:
//                    salon.bookProcedure(scanner);
//                    break;
//                case 5:
//                    salon.cancelBooking1(scanner);
//                    break;
//                case 0:
//                    System.out.println("Exiting...");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//        } while (choice != 0);
//
//        scanner.close();
    }
}