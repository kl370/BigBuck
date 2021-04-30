package controller;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/login")
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uid");
        String passwd = req.getParameter("passw");
        User user = new User();
        boolean ret = user.login(username, passwd);
        if (ret == true) {
            HttpSession session = req.getSession();
            session.setAttribute("username",  username);
            resp.sendRedirect("./index.jsp");
        } else{
            resp.getWriter().println("Failed to login");
        }
    }
}
