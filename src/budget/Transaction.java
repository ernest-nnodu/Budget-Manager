package budget;

public class Transaction {

    //Define fields
    private String name;
    private double amount;
    private TransactionType type;

    //Define constructors

    public Transaction() {

    }

    public Transaction(String name, TransactionType type, double amount) {
        this.name = name;
        this.type = type;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
