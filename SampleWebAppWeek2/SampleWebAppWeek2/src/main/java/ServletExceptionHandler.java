import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletExceptionHandler", value = "/ServletExceptionHandler")
public class ServletExceptionHandler extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        errorProcess(req, resp);
    }

    private void errorProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Exception/Error Details</title></head><body>");

        out.write("<h3>Exception Details</h3>");
        out.write("<ul><li>Servlet Name:" + servletName + "</li>");
        out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
        out.write("<li>Requested URI:" + requestUri + "</li>");
        out.write("<li>Exception Message: You can't divide by 0</li>");
        out.write("</ul>");


        out.write("<br><br>");
        out.write("</body></html>");
    }

}
