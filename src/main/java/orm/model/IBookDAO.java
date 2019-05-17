package orm.model;

public interface IBookDAO {
    void test();
    boolean create(Book book);
    boolean update(Book book);
    boolean delete(int id);
    boolean queryAll();
    boolean get(int id);
}
