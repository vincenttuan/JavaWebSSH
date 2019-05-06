package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import po.MyStock;

public class MyStockDAO {

    private static Context ctx;
    private static DataSource ds;
    Connection conn;

    static {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ssh");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    {
        try {
            conn = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 全部查詢
    public List<MyStock> queryAll() {
        String sql = "select id, symbol, cost, shares, tDate FROM mystock";
        List<MyStock> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
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
        try (Statement stmt = conn.createStatement();
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
    public boolean create(MyStock myStock) {
        String sql = "Insert into myStock (symbol, cost, shares, tDate) Values(?, ?, ?, ?)";
        int row_count = 0;
        Calendar calendar = Calendar.getInstance();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, myStock.getSymbol());
            pstmt.setDouble(2, myStock.getCost());
            pstmt.setInt(3, myStock.getShares());
            // 利用 java.sql.Date 取得 now
            pstmt.setDate(4, new java.sql.Date(calendar.getTime().getTime()));
            row_count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row_count == 0 ? false : true;
    }

    // 修改
    public boolean update(MyStock myStock) {
        String sql = "Update myStock Set symbol=?, cost=?, shares=?, tDate=? Where id = ?";
        int row_count = 0;
        Calendar calendar = Calendar.getInstance();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, myStock.getSymbol());
            pstmt.setDouble(2, myStock.getCost());
            pstmt.setInt(3, myStock.getShares());
            pstmt.setDate(4, new java.sql.Date(calendar.getTime().getTime()));
            pstmt.setInt(5, myStock.getId());
            row_count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row_count == 0 ? false : true;
    }

    // 刪除
    public boolean delete(int id) {
        String sql = "Delete From myStock Where id = ?";
        int row_count = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            row_count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row_count == 0 ? false : true;
    }
}
