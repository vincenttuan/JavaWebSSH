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
    DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/ssh");
    Connection conn = ds.getConnection();
    
    // 3.敘述SQL物件
    Statement stmt = conn.createStatement();
    // 4.結果集合物件
    String sql = "select id, symbol, cost, shares, tDate FROM mystock";
    ResultSet rs = stmt.executeQuery(sql);
%>
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select MyStock JNDI</title>
    </head>
    <body style="padding: 10px">
        <table class="pure-table pure-table-bordered">
            <thead>
                <tr>
                    <th>id</th>
                    <th>symbol</th>
                    <th>cost</th>
                    <th>shares</th>
                    <th>tDate</th>
                </tr>
            </thead>
            <tbody>
                <%
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String symbol = rs.getString("symbol");
                        double cost = rs.getDouble("cost");
                        int shares = rs.getInt("shares");
                        Date tDate = rs.getDate("tDate");
                %>
                    <tr>
                        <td><%=id %></td>
                        <td><%=symbol %></td>
                        <td><%=cost %></td>
                        <td><%=shares %></td>
                        <td><%=tDate %></td>
                    </tr>
                <%
                }
                %>
            </tbody>
        </table>
        
    </body>
</html>
