/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.MyStockService;

@WebServlet("/rest/servlet/*")
public class MyStockController extends BaseController {
    
    private MyStockService service;
    
    @Override
    public void init() {
        tagName = "mystock";
        service = new MyStockService();
    }

    // http://localhost:8080/SSH/rest/servlet/mystock
    // http://localhost:8080/SSH/rest/servlet/mystock/1/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //analysisURI(request, response);
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

}
