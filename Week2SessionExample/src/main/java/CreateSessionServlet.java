import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@WebServlet(name = "create-session", value = "/create-session")
public class CreateSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("sessionAttribute", "This attribute set from Create Session");
        resp.setContentType("text/html");//setting the content type
        PrintWriter pw=resp.getWriter();//get the stream to write the data

        pw.println("<html><body>");
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            pw.println("<h1>"+session.getAttribute(enumeration.nextElement())+"</h1>");
        }
        pw.println("</body></html>");
//        req.getRequestDispatcher("list-session-attributes").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
