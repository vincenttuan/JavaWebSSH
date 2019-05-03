<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="po.MyStock"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>

<%
    // 1.JNDI 查找連線物件
    Context ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ssh");
    Connection conn = ds.getConnection();

    // 3.敘述SQL物件
    Statement stmt = conn.createStatement();
    // 4.結果集合物件
    String sql = "select id, symbol, cost, shares, tDate FROM mystock";
    ResultSet rs = stmt.executeQuery(sql);
    List<MyStock> list = new ArrayList<>();
    while (rs.next()) {
        int id = rs.getInt("id");
        String symbol = rs.getString("symbol");
        double cost = rs.getDouble("cost");
        int shares = rs.getInt("shares");
        Date tDate = rs.getDate("tDate");
        MyStock myStock = new MyStock(id, symbol, cost, shares, tDate);
        list.add(myStock);
    }
    out.print(new Gson().toJson(list));
%>

