import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/private/*")
public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)  servletRequest).getSession(false);
        String username = servletRequest.getParameter("username").trim();
        String password = servletRequest.getParameter("password").trim();
        if (session !=null && username.equals("test") && password.equals("password")){
            session.setAttribute("logged", true);
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            servletRequest.getRequestDispatcher("/public/error.jsp").forward((HttpServletRequest) servletRequest,(HttpServletResponse) servletResponse);
        }
    }

    public void destroy() {

    }
}
