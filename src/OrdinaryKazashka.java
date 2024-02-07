public class OrdinaryKazashka extends User {
    public OrdinaryKazashka(String name, double balance) {
        super(name, balance);
    }
    @Override
    public void bookProcedure(BeautyProcedure procedure, String date, String time) {
        if (getBalance() >= procedure.getPrice()) {
            setBalance(getBalance() - procedure.getPrice()); //чекаем есть ли деньги у не вип казашек чтобы к нам записаться
            for(int i = 0; i < getTakenProcedures().size(); i++){
                if(getTakenProcedures().get(i) == null){
                    getTakenProcedures().set(i, procedure);
                }
            }
            System.out.println("Booking for Ordinary kazashka. Procedure: " + procedure.getName() + " is booked on " + date + " at " + time + ". Price: " + procedure.getPrice() + " subtracted from balance.");
        } else {
            System.out.println("Insufficient balance to book the procedure.");
        }
    }
    @Override
    public void cancelBooking(Booking booking, BeautyProcedure procedure) {
        setBalance(getBalance() + procedure.getPrice());
        System.out.println("Booking for Ordinary kazashka. Procedure: " + booking.getProcedureName() +
                " on " + booking.getDate() + " at " + booking.getTime() +
                " has been canceled. Refund of " + procedure.getPrice() + " added to balance.");

    }
}
