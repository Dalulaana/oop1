import java.util.ArrayList;
public class User {
    private String name;
    private double balance;
    private ArrayList<BeautyProcedure> takenProcedures; // переменные для хранения инфы о клиенте

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.takenProcedures = new ArrayList<>();
    } // конструктор для создания экземпляра класса юзер

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; } // геттеры и сеттеры

    public ArrayList<BeautyProcedure> getTakenProcedures() { return takenProcedures; }
    public void addProcedure(BeautyProcedure procedure) { this.takenProcedures.add(procedure); }
} // метод чтобы добавить процедуру в список процедур
