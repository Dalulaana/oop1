public class BeautyProcedure {
    private String name;
    private double price;
    private String description; // переменные для хранения инфы о процедуре

    public BeautyProcedure(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    } // конструктор класса для создания его экземпляра

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
} // методы геттеры и сеттеры для допуска другими классами к нэйму прайсу и дескрипшн
