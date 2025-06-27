package entities;

public class Account {
    private double revenues;
    private double expenses;
    private double balance;
    
    public Account (double revenues, double expenses){
        this.revenues=revenues;
        this.expenses=expenses;
    }

    public double getRevenues() {
        return revenues;
    }

    public void setRevenues(double revenues) {
        this.revenues = revenues;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance() {
        balance = revenues - expenses;
    }
}
