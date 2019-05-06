package po;

public class MyStock {
    private int id;
    private String symbol;
    private double cost;
    private int shares;
    private long tDate;

    public MyStock() {
    }
    
    // 查詢用
    public MyStock(int id, String symbol, double cost, int shares, long tDate) {
        this.id = id;
        this.symbol = symbol;
        this.cost = cost;
        this.shares = shares;
        this.tDate = tDate;
    }
    
    // 修改用
    public MyStock(int id, String symbol, double cost, int shares) {
        this.id = id;
        this.symbol = symbol;
        this.cost = cost;
        this.shares = shares;
    }
    
    // 新增用
    public MyStock(String symbol, double cost, int shares) {
        this.id = id;
        this.symbol = symbol;
        this.cost = cost;
        this.shares = shares;
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

    public long gettDate() {
        return tDate;
    }

    public void settDate(long tDate) {
        this.tDate = tDate;
    }
    
    
}
