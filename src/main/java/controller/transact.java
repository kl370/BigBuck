package controller;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;


@WebServlet("/transact")
public class transact extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        String action = req.getParameter("action");
        String stockname = req.getParameter("stockname");
        int sharesize = Integer.parseInt(req.getParameter("sharesize"));
        String username = (String)req.getSession().getAttribute("username");
        User user = new User();
        Transaction transaction = new Transaction();
        int userid = user.GetUserid(username);
        boolean ret = false;
        if (action.equals("buy")) {
            ret = transaction.Buy(userid, stockname, sharesize);
        } else{
            ret = transaction.Sell(userid, stockname, sharesize);
        }
        if (ret) {
            json.put("ret", 0);
        } else {
            json.put("ret", 1);
        }
        resp.getWriter().println(json.toString());
    }
}

