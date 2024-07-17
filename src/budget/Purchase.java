package budget;

public class Purchase {

    //Define fields
    private String name;
    private double amount;

    //Define constructor

    public Purchase(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    //Define getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
