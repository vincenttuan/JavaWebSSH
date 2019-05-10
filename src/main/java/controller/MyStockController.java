/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import po.MyStock;
import services.MyStockService;

@WebServlet("/rest/servlet/*")
public class MyStockController extends BaseController {
    
    private MyStockService service;
    
    @Override
    public void init() {
        tagName = "mystock";
        service = new MyStockService();
    }
    // 門戶洞開
    private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.setHeader("Access-Control-Allow-Origin", "*");
      resp.setHeader("Access-Control-Allow-Methods", "*");
    }
    // 單/多筆查詢
    // http://localhost:8080/SSH/rest/servlet/mystock
    // http://localhost:8080/SSH/rest/servlet/mystock/1/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //analysisURI(request, response);
        setAccessControlHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            RestRequest restRequest = new RestRequest(request.getPathInfo());
            int id = restRequest.getId();
            if (id == -1) {
                out.print(service.queryAll());
            } else {
                out.print(service.get(id));
            }
        } catch (ServletException e) {
            e.printStackTrace();
            out.println(e.toString());
        }

    }
    
    // 新增資料
    // http://localhost:8080/SSH/rest/servlet/mystock/
    // body : symbol=xxx&cost=xxx&shares=xxx
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            RestRequest restRequest = new RestRequest(request.getPathInfo());
            String symbol = request.getParameter("symbol");
            double cost = Double.parseDouble(request.getParameter("cost"));
            int shares = Integer.parseInt(request.getParameter("shares"));
            MyStock myStock = new MyStock(symbol, cost, shares);
            out.print(service.create(myStock));
        } catch (ServletException e) {
            e.printStackTrace();
            out.println(e.toString());
        }

    }
    
    // 修改資料
    // http://localhost:8080/SSH/rest/servlet/mystock/1
    // body : symbol=xxx&cost=xxx&shares=xxx
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            RestRequest restRequest = new RestRequest(request.getPathInfo());
            int id = restRequest.getId();

            if (id == -1) {
                out.print("Path invalid !");
            } else {
                String args = new BufferedReader(new InputStreamReader(request.getInputStream())).readLine();
                Map<String, String> map = splitArgs(args);
                String symbol = map.get("symbol");
                double cost = Double.parseDouble(map.get("cost"));
                int shares = Integer.parseInt(map.get("shares"));
                MyStock myStock = new MyStock(id, symbol, cost, shares);
                out.print(service.update(myStock));
            }
        } catch (ServletException e) {
            e.printStackTrace();
            out.println(e.toString());
        }

    }
    
    // 刪除資料
    // http://localhost:8080/SSH/rest/servlet/mystock/1
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            RestRequest restRequest = new RestRequest(request.getPathInfo());
            int id = restRequest.getId();

            if (id == -1) {
                out.print("Path invalid !");
            } else {
                out.print(service.delete(id));
            }
        } catch (ServletException e) {
            e.printStackTrace();
            out.println(e.toString());
        }

    }

}
