public class Booking {
    private int id;
    private String procedureName;
    private String date;
    private String time; // переменные для хранения инфы о записи на процедуру

    public Booking(int id, String procedureName, String date, String time) {
        this.id = id;
        this.procedureName = procedureName;
        this.date = date;
        this.time = time;
    } // конструктор для создания экземпляра класса букинг

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProcedureName() { return procedureName; }
    public void setProcedureName(String procedureName) { this.procedureName = procedureName; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    // добавила

} // методы геттеры и сеттеры позволяют получать и устанавливать значения приватных полей, следуя принципам инкапсуляции
