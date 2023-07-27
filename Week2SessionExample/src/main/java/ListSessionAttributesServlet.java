import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@WebServlet(name = "list-session-attributes", value = "/list-session-attributes")
public class ListSessionAttributesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session ==null){
            return;
        }
        session.setAttribute("sessionAttribute2", "This attribute set from list-session-attributes Session");
        resp.setContentType("text/html");//setting the content type
        PrintWriter pw=resp.getWriter();//get the stream to write the data

        pw.println("<html><body>");
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            pw.println("<h1>"+session.getAttribute(enumeration.nextElement())+"</h1>");
        }
        pw.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
