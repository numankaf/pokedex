import Entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "searchContact", value = "/searchContact")
public class SearchContactServlet extends HttpServlet {
    DatabaseOperations databaseOperations;
    @Override
    public void init() throws ServletException {
        try {
            this.databaseOperations = new DatabaseOperations();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/search-contact.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        List<Contact> contacts = databaseOperations.searchContact(name);

        req.setAttribute("searchedContacts", contacts);
        req.getRequestDispatcher("/jsp/edit-contact.jsp").forward(req,resp);
    }
}
