package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import po.MyStock;

public class MyStockDAO {
    Context ctx;
    DataSource ds;
    Connection conn;
            
    {
        try {
            Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ssh");
        Connection conn = ds.getConnection();
        } catch (Exception e) {
        }
    }
    
    // 全部查詢
    public List<MyStock> queryAll() {
        String sql = "select id, symbol, cost, shares, tDate FROM mystock";
        List<MyStock> list = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String symbol = rs.getString("symbol");
                double cost = rs.getDouble("cost");
                int shares = rs.getInt("shares");
                long tDate = rs.getDate("tDate").getTime();
                MyStock myStock = new MyStock(id, symbol, cost, shares, tDate);
                list.add(myStock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 查詢單筆
    public MyStock get(int id) {
        String sql = "select id, symbol, cost, shares, tDate FROM mystock Where id = " + id;
        MyStock myStock = null;
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);) {
            if (rs.next()) {
                id = rs.getInt("id");
                String symbol = rs.getString("symbol");
                double cost = rs.getDouble("cost");
                int shares = rs.getInt("shares");
                long tDate = rs.getDate("tDate").getTime();
                myStock = new MyStock(id, symbol, cost, shares, tDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return myStock;
    }
    
    // 新增
    public int create(MyStock myStock) {
        return 0;
    }
    
    // 修改
    public int update(MyStock myStock) {
        return 0;
    }
    
    // 刪除
    public int delete(int id) {
        return 0;
    }
}
