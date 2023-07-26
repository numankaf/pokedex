import Entity.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "editContact", value = "/editContact")
public class EditContactServlet extends HttpServlet {
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
        req.getRequestDispatcher("/WEB-INF/jsp/edit-contact.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name").trim();
        String gsm = req.getParameter("gsm").trim();
        Integer id = Integer.parseInt(req.getParameter("id"));
        Contact contact = new Contact(name, gsm);
        contact.setId(id);
        databaseOperations.updateContact(contact);

        req.setAttribute("createdContact", contact);
        req.getRequestDispatcher("/WEB-INF/jsp/operation-successful.jsp").forward(req,resp);
    }
}
