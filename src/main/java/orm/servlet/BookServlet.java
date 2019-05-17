package orm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import orm.dao.BookDAOImpl;
import orm.model.Book;

@WebServlet("/orm/servlet/BookServlet")
public class BookServlet extends HttpServlet {
    private BookDAOImpl dao = new BookDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book("Kotlin", 150);
        boolean create_flag = dao.create(book);
        resp.getWriter().print(create_flag);
    }
    
}
