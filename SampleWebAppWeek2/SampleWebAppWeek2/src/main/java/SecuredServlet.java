import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "secure", value = "/secure")
public class SecuredServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer url = req.getRequestURL();
        System.out.println(url);
        System.out.println(req.getRequestURI());
        if(url.indexOf("secure")!=-1){
            resp.getWriter().write("<h1>Success , This is a secure page </h1>");
        }else{
            resp.setStatus(403);
        }

    }
}
