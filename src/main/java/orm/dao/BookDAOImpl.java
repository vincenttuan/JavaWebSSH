package orm.dao;

import java.util.Set;
import org.hibernate.cfg.Configuration;
import orm.model.Book;

public class BookDAOImpl implements IBookDAO {
    public static void main(String[] args) {
        new BookDAOImpl().test();
    }
    @Override
    public void test() {
        Configuration cfg = new Configuration().configure();
        System.out.println(cfg.configure().getProperty("hibernate.connection.datasource"));
        System.out.println(cfg.configure().getProperty("hibernate.dialect"));
        System.out.println(cfg.configure().getProperty("current_session_context_class"));
        System.out.println(cfg.configure().getProperty("cache.provider_class"));
        System.out.println(cfg.configure().getProperty("show_sql"));
    }

    @Override
    public boolean create(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Book> queryAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
