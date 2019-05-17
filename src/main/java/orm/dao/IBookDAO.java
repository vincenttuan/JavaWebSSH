package orm.dao;

import java.util.Set;
import orm.model.Book;

public interface IBookDAO {
    void test();
    boolean create(Book book);
    boolean update(Book book);
    boolean delete(int id);
    Set<Book> queryAll();
    Book get(int id);
}
