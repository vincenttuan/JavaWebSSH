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
    
    public List<MyStock> queryAll() {
        return null;
    }
    
    public MyStock get(int id) {
        return null;
    }
    
    public int create(MyStock myStock) {
        return 0;
    }
    
    public int update(MyStock myStock) {
        return 0;
    }
    
    public int delete(int id) {
        return 0;
    }
}
