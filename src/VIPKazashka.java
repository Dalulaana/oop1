public class VIPKazashka extends User {
    public VIPKazashka(String name, double balance) {
        super(name, balance); //вызываем конструктор юзера
    }
    @Override
    public void bookProcedure(BeautyProcedure procedure, String date, String time) {
        // вип казашка записывается бесплатно
        System.out.println("Booking for VIP kazashka. Procedure: " + procedure.getName() + " is booked for free on " + date + " at " + time);
    }
}
