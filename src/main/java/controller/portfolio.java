package controller;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;


@WebServlet("/portfolio")
public class portfolio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        String username = (String)req.getSession().getAttribute("username");
        User user = new User();
        int userid = user.GetUserid(username);
        Dictionary<String, Integer> stocks = user.GetStocks(userid);
        for (Enumeration k = stocks.keys(); k.hasMoreElements();) {
            String stockname = (String) k.nextElement();
            JSONObject temp = new JSONObject();
            temp.put("stockname", stockname);
            temp.put("sharesize", stocks.get(stockname));
            Stock stock = YahooFinance.get(stockname);
            StockQuote price = stock.getQuote();
            temp.put("currentprice", price.getPrice());
            jsonArr.put(temp);
        }
        json.put("stocks", jsonArr);
        resp.getWriter().println(json.toString());
    }
}


