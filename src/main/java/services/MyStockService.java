package services;

import com.google.gson.Gson;
import dao.MyStockDAO;
import po.MyStock;

/*******************************************
 * 1.與 MyStockDAO 互動
 * 2.轉 JSON
********************************************/
public class MyStockService {
    private MyStockDAO dao = new MyStockDAO();
    private Gson gson = new Gson();  
    
    // 全部查詢
    public String queryAll() {
        return gson.toJson(dao.queryAll());
    }
    
    // 查詢單筆
    public String get(int id) {
        return gson.toJson(dao.get(id));
    }
    
    // 新增
    public String create(MyStock myStock) {
        return gson.toJson(dao.create(myStock));
    }
    
    // 修改
    public String update(MyStock myStock) {
        if(get(myStock.getId()) != null) {
            return gson.toJson(dao.update(myStock));
        } else {
            return null;
        }
    }
    
    // 刪除
    public String delete(int id) {
        return gson.toJson(dao.delete(id));
    }
    
}
