import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "atm", value = "/atm")
public class ATMServlet extends HttpServlet {
    private double balance=0.0;

    @Override
    public void  doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int money = Integer.parseInt(req.getParameter("money").trim());
        if(balance-money>=0){
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized(this){
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= money;
            }
//            balance -= money;
        }


        resp.getWriter().write("<h1> Balance : "+Double.toString(balance)+"<h1>");
        System.out.println(balance);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int money = Integer.parseInt(req.getParameter("money").trim());
        balance += money;
        resp.getWriter().write("<h1> Balance : "+Double.toString(balance)+"<h1>");
        System.out.println(balance);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
