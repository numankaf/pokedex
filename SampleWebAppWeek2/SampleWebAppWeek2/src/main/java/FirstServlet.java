import Entity.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FirstServlet", value = "/FirstServlet")
public class FirstServlet extends HttpServlet {
    List<Person> characters ;
    @Override
    public void init() {
        characters = new ArrayList<>();
        characters.add(new Person("https://i.pinimg.com/736x/51/9c/ff/519cffb8cb7dd9f31137cc4fee468c68.jpg","Itadori Yuuji", "Jujutsu Kaisen",17));
        characters.add(new Person("https://ae01.alicdn.com/kf/H798bf54a4ae2429a8d4464c54679fa3et.png", "Minato Namikaze","Naruto",21));
        characters.add(new Person("https://i.pinimg.com/736x/51/9c/ff/519cffb8cb7dd9f31137cc4fee468c68.jpg","Itadori Yuuji", "Jujutsu Kaisen",17));
        characters.add(new Person("https://ae01.alicdn.com/kf/H798bf54a4ae2429a8d4464c54679fa3et.png", "Minato Namikaze","Naruto",21));
        characters.add(new Person("https://i.pinimg.com/736x/51/9c/ff/519cffb8cb7dd9f31137cc4fee468c68.jpg","Itadori Yuuji", "Jujutsu Kaisen",17));
        characters.add(new Person("https://ae01.alicdn.com/kf/H798bf54a4ae2429a8d4464c54679fa3et.png", "Minato Namikaze","Naruto",21));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("characters", characters);
        request.getRequestDispatcher("characters.jsp").forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String imgUrl = request.getParameter("imgUrl").trim();
        String name = request.getParameter("name").trim();
        String anime = request.getParameter("anime").trim();
        int age = Integer.parseInt(request.getParameter("age").trim());
        characters.add(new Person(imgUrl,name,anime,age));
        request.setAttribute("characters", characters);
        request.getRequestDispatcher("characters.jsp").forward(request, response);
    }


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
