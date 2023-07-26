
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "exceptionExample", value = "/exceptionExample")
public class EceptionExampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int firstNum = Integer.parseInt(req.getParameter("firstNum").trim());
        int secondNum = Integer.parseInt(req.getParameter("secondNum").trim());
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.write("<html><head><title>Divide Result</title></head><body>");
        out.write("<h3> Result = " + firstNum / secondNum + "</h3>");
        out.write("<br><br>");
        out.write("</body></html>");

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
