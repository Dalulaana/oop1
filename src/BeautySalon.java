import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BeautySalon {
    private ArrayList<User> users;
    private ArrayList<BeautyProcedure> procedures;
    private ArrayList<Booking> bookingHistory; // список процедур юзеров и записи на процедуру

    public BeautySalon() {
        users = new ArrayList<>();
        procedures = new ArrayList<>();
        bookingHistory = new ArrayList<>();
    } // конструктор который делает объект бьюти салон с пустыми эррэй листами

//    public void displayProcedures() {
//        if (procedures.isEmpty()) {
//            System.out.println("No procedures available.");
//            return;
//        }
//        for (BeautyProcedure procedure : procedures) {
//            System.out.println("Procedure: " + procedure.getName() + ", Price: " + procedure.getPrice() + ", Description: " + procedure.getDescription());
//        }
//    } // метод для отображения списка процедур
    public void displayProcedures(Connection conn) throws SQLException {
        String sql = "SELECT * FROM BeautyProcedures";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Procedure: " + rs.getString("name") + ", Price: " + rs.getDouble("price") + ", Description: " + rs.getString("description"));
            }
        }
    }

    public void updateProcedure(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID of the procedure to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();

        String sql = "UPDATE BeautyProcedures SET name = ?, price = ?, description = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, description);
            pstmt.setInt(4, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Procedure updated successfully.");
            } else {
                System.out.println("Procedure not found.");
            }
        }
    }

    public void deleteProcedure(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID of the procedure to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM BeautyProcedures WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Procedure deleted successfully.");
            } else {
                System.out.println("Procedure not found.");
            }
        }
    }

    public void addProcedure(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter procedure name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        String sql = "INSERT INTO BeautyProcedures (name, price, description) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setString(3, description);
            pstmt.executeUpdate();
            System.out.println("Procedure added successfully.");
        }
    }
//    public void addProcedure(Scanner scanner) {
//        System.out.print("Enter procedure name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter price: ");
//        double price = scanner.nextDouble();
//        scanner.nextLine();
//        System.out.print("Enter description: ");
//        String description = scanner.nextLine();
//
//        BeautyProcedure procedure = new BeautyProcedure(name, price, description);
//        procedures.add(procedure);
//        System.out.println("Procedure added successfully.");
//    } // метод для того чтобы добавить процедуру
//    public void loadProceduresFromDatabase(Connection conn) throws SQLException {
//        String sql = "SELECT * FROM BeautyProcedures";
//        procedures.clear(); // Clear current procedures
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                BeautyProcedure procedure = new BeautyProcedure(
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getString("description"));
//                procedures.add(procedure); // добвляем в эррэй лист из бд
//            }
//        }
//    }

    public void addUser(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter user type (1 for VIP Kazashka, 2 for Ordinary Kazashka): ");
        int userType = scanner.nextInt();
        scanner.nextLine();

        String userTypeStr = (userType == 1) ? "VIP" : "Ordinary";

        String sql = "INSERT INTO Users (name, balance, userType) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, balance);
            pstmt.setString(3, userTypeStr);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User added successfully.");
                // добавила
                if (userType == 1) {
                    System.out.println("VIP Kazashka added successfully.");
                }
                else {
                    System.out.println("Ordinary Kazashka added successfully.");
                }
                // конец
            } else {
                System.out.println("User could not be added.");
            }
        }

    }
//    public void addUser(Scanner scanner) {
//        System.out.print("Enter user name: ");
//        String name = scanner.nextLine();
//        System.out.print("Enter initial balance: ");
//        double balance = scanner.nextDouble();
//        scanner.nextLine();
//
//        System.out.print("Enter user type (1 for VIP Kazashka, 2 for Ordinary Kazashka): ");
//        int userType = scanner.nextInt();
//        scanner.nextLine(); // Consume newline left by nextInt()
//
//        User user;
//        if (userType == 1) {
//            user = new VIPKazashka(name, balance);
//            System.out.println("VIP Kazashka added successfully.");
//        } else {
//            user = new OrdinaryKazashka(name, balance);
//            System.out.println("Ordinary Kazashka added successfully.");
//        }
//
//        users.add(user);
//        //User user = new User(name, balance);
//        //users.add(user);
//        //System.out.println("User added successfully."); // метод чтобы добавить клиента
//    }
    public void usersFromDatabase(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Users";
        users.clear();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user;
                String userTypeStr = rs.getString("userType");
                if (userTypeStr.equals("VIP")) {
                    user = new VIPKazashka(
                            rs.getString("name"),
                            rs.getDouble("balance")
                    );
                } else {
                    user = new OrdinaryKazashka(
                            rs.getString("name"),
                            rs.getDouble("balance")
                    );
                }
                users.add(user); // добавить юзеров в эррэй лист из бд
            }
        }
    }

    public void bookProcedure(Connection conn, Scanner scanner) throws SQLException {
//        if (users.isEmpty()) {
//            System.out.println("No users available to book a procedure.");
//            return;
//        }
//        if (procedures.isEmpty()) {
//            System.out.println("No beauty procedures available to book.");
//            return;
//        }

        String usersQuery = "SELECT COUNT(*) AS userCount FROM Users";
        try (Statement usersStatement = conn.createStatement();
             ResultSet usersResult = usersStatement.executeQuery(usersQuery)) {
            if (usersResult.next()) {
                int userCount = usersResult.getInt("userCount");
                if (userCount == 0) {
                    System.out.println("No users available to book a procedure.");
                    return;
                }
            }
        }

        // Check if Procedures table is empty
        String proceduresQuery = "SELECT COUNT(*) AS procedureCount FROM BeautyProcedures";
        try (Statement proceduresStatement = conn.createStatement();
             ResultSet proceduresResult = proceduresStatement.executeQuery(proceduresQuery)) {
            if (proceduresResult.next()) {
                int procedureCount = proceduresResult.getInt("procedureCount");
                if (procedureCount == 0) {
                    System.out.println("No beauty procedures available to book.");
                    return;
                }
            }
        }

        System.out.println("Select a user by name:");
//        for (User user : users) {
//            System.out.println(user.getName());
//        }
//        String userName = scanner.nextLine();
//        User selectedUser = null;
//        for (User user : users) {
//            if (user.getName().equalsIgnoreCase(userName)) {
//                selectedUser = user;
//                break;
//            }
//        }
//        if (selectedUser == null) {
//            System.out.println("User not found.");
//            return;
//        }
        String userName = scanner.nextLine();
        String userNamesQuery = "SELECT name, userType, balance FROM Users WHERE name = ?";
        String userType;
        double balance;
        try (PreparedStatement userNamesStatement = conn.prepareStatement(userNamesQuery)) {
            userNamesStatement.setString(1, userName);
            try (ResultSet userNamesResult = userNamesStatement.executeQuery()) {
                if (!userNamesResult.next()) {
                    System.out.println("User not found.");
                    return;
                }
                // added
                userType = userNamesResult.getString("userType");
                balance = userNamesResult.getDouble("balance");
            }
        }

        System.out.println("Select a procedure by name:");
//        for (BeautyProcedure procedure : procedures) {
//            System.out.println(procedure.getName());
//        }
//        String procedureName = scanner.nextLine();
//        BeautyProcedure selectedProcedure = null;
//        for (BeautyProcedure procedure : procedures) {
//            if (procedure.getName().equalsIgnoreCase(procedureName)) {
//                selectedProcedure = procedure;
//                break;
//            }
//        }
//        if (selectedProcedure == null) {
//            System.out.println("Procedure not found.");
//            return;
//        }
        String procedureName = scanner.nextLine();
        String procedureNamesQuery = "SELECT name, description, price FROM BeautyProcedures WHERE name = ?";
        BeautyProcedure procedure;
        try (PreparedStatement procedureNamesStatement = conn.prepareStatement(procedureNamesQuery)) {
            procedureNamesStatement.setString(1, procedureName);
            try (ResultSet procedureNamesResult = procedureNamesStatement.executeQuery()) {
                if (procedureNamesResult.next()) {
                    String procedureName1 = procedureNamesResult.getString("name");
                    String description = procedureNamesResult.getString("description");
                    double price = procedureNamesResult.getDouble("price");
                    procedure = new BeautyProcedure.Builder(procedureName1, price).description(description).build();
                } else {
                    System.out.println("Procedure not found.");
                    return;
                }
            }
        }

        System.out.print("Enter the date for the booking (format MM/dd/yyyy): ");
        String date = scanner.nextLine();
        System.out.print("Enter the time for the booking (format HH:mm): ");
        String time = scanner.nextLine();

//        if (selectedUser instanceof VIPKazashka) {
//            ((VIPKazashka) selectedUser).bookProcedure(selectedProcedure, date, time);
//        }
//        else if (selectedUser instanceof OrdinaryKazashka) {
//            ((OrdinaryKazashka) selectedUser).bookProcedure(selectedProcedure, date, time);
//        }

        if (userType.equals("VIP")) {
            VIPKazashka vipUser = new VIPKazashka(userName, balance);
            vipUser.bookProcedure(procedure, date, time);
        }
        else if (userType.equals("Ordinary")) {
            OrdinaryKazashka ordinaryUser = new OrdinaryKazashka(userName, balance);
            ordinaryUser.bookProcedure(procedure, date, time);

            String updateBalanceQuery = "UPDATE Users SET balance = balance - ? WHERE name = ?";
            try (PreparedStatement updateBalanceStatement = conn.prepareStatement(updateBalanceQuery)) {
                updateBalanceStatement.setDouble(1, procedure.getPrice());
                updateBalanceStatement.setString(2, userName);
                updateBalanceStatement.executeUpdate();
            }
        }
        else {
            System.out.println("Invalid user type.");
            return;
        }

        int userId;
        String getUserIdSql = "SELECT id FROM Users WHERE name = ?";
        try (PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdSql)) {
            getUserIdStmt.setString(1, userName);
            ResultSet rs = getUserIdStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id");
            } else {
                System.out.println("Error: User ID not found for user " + userName);
                return;
            }
        }

        int procedureId;
        String getProcedureIdSql = "SELECT id FROM BeautyProcedures WHERE name = ?";
        try (PreparedStatement getProcedureIdStmt = conn.prepareStatement(getProcedureIdSql)) {
            getProcedureIdStmt.setString(1, procedureName);
            ResultSet rs = getProcedureIdStmt.executeQuery();
            if (rs.next()) {
                procedureId = rs.getInt("id");
            } else {
                System.out.println("Error: Procedure ID not found for procedure " + procedureName);
                return;
            }
        }

        String sql = "INSERT INTO Bookings (userId, procedureId, date, time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId); // Assuming User has an id field
            pstmt.setInt(2, procedureId); // Assuming BeautyProcedure has an id field
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error occurred while booking procedure: " + e.getMessage());
            return;
        }

//        Booking newBooking = new Booking(bookingHistory.size() + 1, selectedProcedure.getName(), date, time);
//        bookingHistory.add(newBooking);
//        selectedUser.addProcedure(selectedProcedure);

        //System.out.println("Booking successful for " + selectedUser.getName() + " for the procedure " + selectedProcedure.getName() + " on " + date + " at " + time);
    } // метод упрощает бронирование для клиента, он выбирает юзера, процедуру, вводит дату и время. запись сохраняется в истории

    public void loadBookingsFromDatabase(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Bookings";
        bookingHistory.clear(); // Clear current bookings
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String procedureId = rs.getString("procedureId");
                String date = rs.getString("date");
                String time = rs.getString("time");

                // Create a new Booking object
                Booking newBooking = new Booking(userId, procedureId, date, time);
                bookingHistory.add(newBooking); // Add the booking to the ArrayList
            }
        }
    }


    public void cancelBooking1(Connection conn, Scanner scanner) throws SQLException {
//        if (bookingHistory.isEmpty()) {
//            System.out.println("There are no bookings to cancel.");
//            return;
//        }
        String bookingQuery = "SELECT * FROM Bookings";
        try (Statement bookingsStatement = conn.createStatement();
             ResultSet bookingsResult = bookingsStatement.executeQuery(bookingQuery)) {
            if (!bookingsResult.next()) {
                System.out.println("There are no bookings available to cancel.");
                return;
            }
            // added
            System.out.println("Current bookings:");
            do {
                int bookingId = bookingsResult.getInt("id");
                int userId = bookingsResult.getInt("userId");
                int procedureId = bookingsResult.getInt("procedureId");
                String date = bookingsResult.getString("date");
                String time = bookingsResult.getString("time");

                // Fetch name from Users table
                String userNameQuery = "SELECT name FROM Users WHERE id = ?";
                try (PreparedStatement userNameStatement = conn.prepareStatement(userNameQuery)) {
                    userNameStatement.setInt(1, userId);
                    ResultSet userResult = userNameStatement.executeQuery();
                    String userName = userResult.next() ? userResult.getString("name") : "Unknown";

                    // Fetch procedure name from BeautyProcedures table
                    String procedureNameQuery = "SELECT name FROM BeautyProcedures WHERE id = ?";
                    try (PreparedStatement procedureNameStatement = conn.prepareStatement(procedureNameQuery)) {
                        procedureNameStatement.setInt(1, procedureId);
                        ResultSet procedureResult = procedureNameStatement.executeQuery();
                        String procedureName = procedureResult.next() ? procedureResult.getString("name") : "Unknown";

                        System.out.println("ID: " + bookingId + ", User: " + userName + ", Procedure: " + procedureName + ", Date: " + date + ", Time: " + time);
                    }
                }
            } while (bookingsResult.next());
        }
//        System.out.println("Current bookings:");
//        for (Booking booking : bookingHistory) {
//            System.out.println("ID: " + booking.getId() + ", Procedure: " + booking.getProcedureName() + ", Date: " + booking.getDate() + ", Time: " + booking.getTime());
//        }

        System.out.print("Enter the ID of the booking to cancel: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

//        boolean found = false;
//        Iterator<Booking> iterator = bookingHistory.iterator();
//        while (iterator.hasNext()) {
//            Booking booking = iterator.next();
//            if (booking.getId() == bookingId) {
//                found = true;
//                User selectedUser = getUserByName(booking.getProcedureName());
//                if (selectedUser instanceof VIPKazashka) {
//                    selectedUser.cancelBooking(booking, getProcedureByName(booking.getProcedureName()));
//                } else if (selectedUser instanceof OrdinaryKazashka) {
//                    selectedUser.cancelBooking(booking, getProcedureByName(booking.getProcedureName()));
//                }
//                else{
//                    System.out.println("hello");
//                }
//                iterator.remove();
//                System.out.println("Booking with ID " + bookingId + " has been canceled.");
//                found = true;
//                break;
//            }
//        }
//
//        if (!found) {
//            System.out.println("No booking found with ID " + bookingId);
//        }

        String selectBookingQuery = "SELECT * FROM Bookings WHERE id = ?";
        double price = 0.0;
        int userId = 0;
        try (PreparedStatement selectBookingStatement = conn.prepareStatement(selectBookingQuery)) {
            selectBookingStatement.setInt(1, bookingId);
            ResultSet bookingResult = selectBookingStatement.executeQuery();
            if (!bookingResult.next()) {
                System.out.println("No booking found with ID " + bookingId);
                return;
            }
            userId = bookingResult.getInt("userId");
            int procedureId = bookingResult.getInt("procedureId");
            // Fetch the price of the procedure
            String getPriceQuery = "SELECT price FROM BeautyProcedures WHERE id = ?";
            try (PreparedStatement getPriceStatement = conn.prepareStatement(getPriceQuery)) {
                getPriceStatement.setInt(1, procedureId);
                ResultSet priceResult = getPriceStatement.executeQuery();
                if (priceResult.next()) {
                    price = priceResult.getDouble("price");
                }
            }
        }

        String sql = "DELETE FROM Bookings WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId); // Set the booking ID as a parameter
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking with ID " + bookingId + " has been canceled and removed from the database.");

                String updateBalanceQuery = "UPDATE Users SET balance = balance + ? WHERE id = ? AND userType = 'Ordinary'";
                try (PreparedStatement updateBalanceStatement = conn.prepareStatement(updateBalanceQuery)) {
                    updateBalanceStatement.setDouble(1, price);
                    updateBalanceStatement.setInt(2, userId);
                    int balanceRowsAffected = updateBalanceStatement.executeUpdate();
                    if (balanceRowsAffected > 0) {
                        System.out.println("Refund of " + price + " added to balance for user.");
                    } else {
                        System.out.println("User is not of type 'Ordinary' or user not found.");
                    }
                }
            }
            else {
                System.out.println("No booking found with ID " + bookingId);
            }
        }
        catch (SQLException e) {
            System.out.println("Error deleting booking: " + e.getMessage());
        }
    } // отмена записи

//    private User getUserByName(String name) {
//        for (User user : users) {
//            for(int i = 0; i < user.getTakenProcedures().size(); i++){
//                if (user.getTakenProcedures().get(i) != null && user.getTakenProcedures().get(i).getName().equalsIgnoreCase(name)) {
//                    return user;
//                }
//            }
//        }
//        return null;
//    }
//
//    private BeautyProcedure getProcedureByName(String name) {
//        for (BeautyProcedure procedure : procedures) {
//            if (procedure.getName().equalsIgnoreCase(name)) {
//                return procedure;
//            }
//        }
//        return null;
//    }
}
