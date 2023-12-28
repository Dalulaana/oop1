import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
public class BeautySalon {
    private ArrayList<User> users;
    private ArrayList<BeautyProcedure> procedures;
    private ArrayList<Booking> bookingHistory; // список процедур юзеров и записи на процедуру

    public BeautySalon() {
        users = new ArrayList<>();
        procedures = new ArrayList<>();
        bookingHistory = new ArrayList<>();
    } // конструктор который делает объект бьюти салон с пустыми эррэй листами

    public void displayProcedures() {
        if (procedures.isEmpty()) {
            System.out.println("No procedures available.");
            return;
        }
        for (BeautyProcedure procedure : procedures) {
            System.out.println("Procedure: " + procedure.getName() + ", Price: " + procedure.getPrice() + ", Description: " + procedure.getDescription());
        }
    } // метод для отображения списка процедур

    public void addProcedure(Scanner scanner) {
        System.out.print("Enter procedure name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        BeautyProcedure procedure = new BeautyProcedure(name, price, description);
        procedures.add(procedure);
        System.out.println("Procedure added successfully.");
    } // метод для того чтобы добавить процедуру

    public void addUser(Scanner scanner) {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        User user = new User(name, balance);
        users.add(user);
        System.out.println("User added successfully.");
    } // метод чтобы добавить клиента

    public void bookProcedure(Scanner scanner) {
        if (users.isEmpty()) {
            System.out.println("No users available to book a procedure.");
            return;
        }
        if (procedures.isEmpty()) {
            System.out.println("No beauty procedures available to book.");
            return;
        }

        System.out.println("Select a user by name:");

        for (User user : users) {
            System.out.println(user.getName());
        }
        String userName = scanner.nextLine();
        User selectedUser = null;
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userName)) {
                selectedUser = user;
                break;
            }
        }
        if (selectedUser == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Select a procedure by name:");
        for (BeautyProcedure procedure : procedures) {
            System.out.println(procedure.getName());
        }
        String procedureName = scanner.nextLine();
        BeautyProcedure selectedProcedure = null;
        for (BeautyProcedure procedure : procedures) {
            if (procedure.getName().equalsIgnoreCase(procedureName)) {
                selectedProcedure = procedure;
                break;
            }
        }
        if (selectedProcedure == null) {
            System.out.println("Procedure not found.");
            return;
        }

        System.out.print("Enter the date for the booking (format MM/dd/yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Enter the time for the booking (format HH:mm): ");
        String time = scanner.nextLine();

        Booking newBooking = new Booking(bookingHistory.size() + 1, selectedProcedure.getName(), date, time);
        bookingHistory.add(newBooking);
        selectedUser.addProcedure(selectedProcedure);

        System.out.println("Booking successful for " + selectedUser.getName() + " for the procedure " + selectedProcedure.getName() + " on " + date + " at " + time);
    } // метод упрощает бронирование для клиента, он выбирает юзера, процедуру, вводит дату и время. запись сохраняется в истории

    public void cancelBooking(Scanner scanner) {
        if (bookingHistory.isEmpty()) {
            System.out.println("There are no bookings to cancel.");
            return;
        }

        System.out.println("Current bookings:");
        for (Booking booking : bookingHistory) {
            System.out.println("ID: " + booking.getId() + ", Procedure: " + booking.getProcedureName() + ", Date: " + booking.getDate() + ", Time: " + booking.getTime());
        }

        System.out.print("Enter the ID of the booking to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        Iterator<Booking> iterator = bookingHistory.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getId() == bookingId) {
                iterator.remove();
                System.out.println("Booking with ID " + bookingId + " has been canceled.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No booking found with ID " + bookingId);
        }
    } // отмена записи
}
