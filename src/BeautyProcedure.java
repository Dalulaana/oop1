import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BeautyProcedure {
    private String name;
    private double price;
    private String description; // переменные для хранения инфы о процедуре

    private BeautyProcedure(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
    }

//    public BeautyProcedure(String name, double price, String description) {
//        this.name = name;
//        this.price = price;
//        this.description = description;
//    } // конструктор класса для создания его экземпляра

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public static class Builder {
        private String name;
        private double price;
        private String description;

        public Builder(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        // method to create an instance of BeautyProcedure
        public BeautyProcedure build() {
            return new BeautyProcedure(this);
        }
    }
    // добавила
//    public void create(Connection conn) throws SQLException {
//        String sql = "INSERT INTO BeautyProcedures (name, price, description) VALUES (?, ?, ?)";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, this.name);
//            pstmt.setDouble(2, this.price);
//            pstmt.setString(3, this.description);
//            pstmt.executeUpdate();
//        }
//    }

//    public static ArrayList<BeautyProcedure> getAll(Connection conn) throws SQLException {
//        String sql = "SELECT * FROM BeautyProcedures";
//        ArrayList<BeautyProcedure> procedures = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                BeautyProcedure procedure = new BeautyProcedure(
//                        rs.getString("name"),
//                        rs.getDouble("price"),
//                        rs.getString("description"));
//                procedures.add(procedure);
//            }
//        }
//        return procedures;
//    }
}
