package po;

import java.util.Date;

public class MyStock {
    private int id;
    private String symbol;
    private double cost;
    private int shares;
    private Date tDate;

    public MyStock() {
    }

    public MyStock(int id, String symbol, double cost, int shares, Date tDate) {
        this.id = id;
        this.symbol = symbol;
        this.cost = cost;
        this.shares = shares;
        this.tDate = tDate;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }
    
    
}
