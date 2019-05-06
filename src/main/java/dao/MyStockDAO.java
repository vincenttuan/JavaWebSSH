package dao;

import java.sql.Connection;
import java.util.List;
import java.util.Set;
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
        return null;
    }
    
    // 查詢單筆
    public MyStock get(int id) {
        return null;
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
