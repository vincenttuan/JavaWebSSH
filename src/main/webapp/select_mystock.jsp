<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%
    // 1.建立MySQL資料庫物件實體
    //Class.forName("org.apache.derby.jdbc.ClientDriver");
    // 資料庫連線相關資料
    String url = "jdbc:derby://localhost:1527/ssh";
    String username = "ssh";
    String password = "1234";

    // 2.連線物件
    Connection conn = DriverManager.getConnection(url, username, password);
    // 3.敘述SQL物件
    Statement stmt = conn.createStatement();
    // 4.結果集合物件
    String sql = "select id, symbol, cost, shares, tDate FROM mystock";
    ResultSet rs = stmt.executeQuery(sql);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select MyStock</title>
    </head>
    <body>
        <%
            // 利用 while-loop 走訪 record 內容
            while (rs.next()) {
                int id = rs.getInt("id");
                String symbol = rs.getString("symbol");
                double cost = rs.getDouble("cost");
                int shares = rs.getInt("shares");
                Date tDate = rs.getDate("tDate");
                out.println(id + "," + symbol + "," + cost + "," + shares + "," + tDate + "<br>");
            }
            // 關閉連線
            conn.close();
        %>
    </body>
</html>
