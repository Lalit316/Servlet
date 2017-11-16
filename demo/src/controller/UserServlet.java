package controller;
import domains.User;
import services.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by l on 3/6/2017.
 */
public class UserServlet extends javax.servlet.http.HttpServlet {
    private void checkSession(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        if (page == null) {
            page = "invalid";
        }

        if (!page.equalsIgnoreCase("login") && !page.equalsIgnoreCase("logout")) {
            HttpSession session = request.getSession(false);
            User user = (User)session.getAttribute("user");
            if (user == null) {
                request.setAttribute("msg","Please login First!");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }
        // return page;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String page = request.getParameter("page");
        String message = "";
        checkSession(request, response, page);

        if (page.equalsIgnoreCase("login")) {
            String name = request.getParameter("username");
            String password = request.getParameter("password");

            User user = new UserService().getUser(name, password);
            if (user != null) {

                HttpSession session = request.getSession(false);
                session.setAttribute("user",user);

                message = "Login Successful!!";
                request.setAttribute("msg", message);
                RequestDispatcher rd = request.getRequestDispatcher("user/home.jsp");
                rd.forward(request, response);
            } else {
                message = "Incorect Username or Password!!";
                request.setAttribute("msg", message);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        }
        if (page.equalsIgnoreCase("logout")) {
            HttpSession session = request.getSession(false);
            session.invalidate();
            message = "Successfully Logged out!!";
            request.setAttribute("msg", message);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }

        if (page.equalsIgnoreCase("home")) {
            request.setAttribute("msg", message);
            RequestDispatcher rd = request.getRequestDispatcher("user/home.jsp");
            rd.forward(request, response);
        }
        if (page.equalsIgnoreCase("list")) {
            // get user list from database
            List<User> userList = new UserService().getUserList();
            request.setAttribute("userList", userList);
            RequestDispatcher rd = request.getRequestDispatcher("user/list.jsp");
            rd.forward(request, response);
            // set user list for view
            //forward request to user list page
        }
        if (page.equalsIgnoreCase("userForm")) {
            RequestDispatcher rd = request.getRequestDispatcher("user/userForm.jsp");
            rd.forward(request, response);
        }
        if (page.equalsIgnoreCase("company")) {
            RequestDispatcher rd = request.getRequestDispatcher("user/userCompany.jsp");
            rd.forward(request, response);
        }

        if (page.equalsIgnoreCase("addUser")) {
            User user = new User();
            user.setName(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setRole(request.getParameter("role"));

            new UserService().insert(user);

            List<User> userList = new UserService().getUserList();
            request.setAttribute("userList", userList);
            RequestDispatcher rd = request.getRequestDispatcher("user/list.jsp");
            rd.forward(request, response);

        }
        if (page.equalsIgnoreCase("deleteList")) {
            int id = Integer.parseInt(request.getParameter("id"));
            new UserService().deleteUser(id);

            List<User> userList = new UserService().getUserList();
            request.setAttribute("userList", userList);
            RequestDispatcher rd = request.getRequestDispatcher("user/list.jsp");
            rd.forward(request, response);
        }
        if (page.equalsIgnoreCase("editUser")) {
            User user = new User();
            user.setName(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setRole(request.getParameter("role"));
            user.setId(Integer.parseInt(request.getParameter("id")));

            new UserService().editUser(user);

            List<User> userList = new UserService().getUserList();
            request.setAttribute("userList", userList);
            RequestDispatcher rd = request.getRequestDispatcher("user/list.jsp");
            rd.forward(request, response);

        }
        if (page.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = new UserService().getUser(id);

            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("user/edit.jsp");
            rd.forward(request, response);
        }

        }



    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
