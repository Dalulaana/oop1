public class VIPKazashka extends User {
    public VIPKazashka(String name, double balance) {
        super(name, balance); //вызываем конструктор юзера
    }
    @Override
    public void bookProcedure(BeautyProcedure procedure, String date, String time) {
        // вип казашка записывается бесплатно
        for(int i = 0; i < getTakenProcedures().size(); i++){
            if(getTakenProcedures().get(i) == null){
                getTakenProcedures().set(i, procedure);
            }
        }
        System.out.println("Booking for VIP kazashka. Procedure: " + procedure.getName() + " is booked for free on " + date + " at " + time);
    }
    @Override
    public void cancelBooking(Booking booking, BeautyProcedure procedure) {
        System.out.println("Booking for VIP kazashka. Procedure: " + booking.getProcedureName() +
                " on " + booking.getDate() + " at " + booking.getTime() +
                " has been canceled. Procedure is free.");
    }
}
