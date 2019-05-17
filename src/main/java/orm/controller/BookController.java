package orm.controller;

import controller.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import orm.dao.BookDAOImpl;
import orm.model.Book;

@WebServlet("/orm/bookcontroller/*")
public class BookController extends BaseController {

    private BookDAOImpl dao;

    @Override
    public void init() {
        tagName = "book";
        dao = new BookDAOImpl();
    }
    
    // http://localhost:8080/SSH/rest/bookcontroller/book/1/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RestRequest restRequest = new RestRequest(req.getPathInfo());
        int id = restRequest.getId();
        Book book = dao.get(id);
        resp.getWriter().print(book);
    }

}
