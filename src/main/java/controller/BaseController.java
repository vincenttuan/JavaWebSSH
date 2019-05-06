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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController extends HttpServlet {
    protected String tagName;
    
    protected void analysisURI(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("method : " + request.getMethod());
        out.println("RequestURI : " + request.getRequestURI() + "\n");
        out.println("getContextPath() : " + request.getContextPath());
        out.println("getServletPath() : " + request.getServletPath());
        out.println("getPathInfo() : " + request.getPathInfo() + "\n");
        
        BaseController.RestRequest restRequest = new BaseController.RestRequest(request.getPathInfo());
        out.println("restRequest.getId() : " + (restRequest.getId() == -1 ? "null":restRequest.getId()) + "\n");

        out.println("getParameterMap().size() : " + request.getParameterMap().size());
        out.println("getParameterMap() : " + request.getParameterMap());
        Map map = request.getParameterMap();

        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String paramName = (String) e.nextElement();
            String[] values = request.getParameterValues(paramName);
            for (int i = 0; i < values.length; i++) {
                out.println(paramName + "[" + i + "] = " + values[i]);
            }
        }
        out.println();
    }
    
    // args to Map
    // Ex:
    // name=vincent%age=18 轉成 {"name":"vincent", "age":18}
    protected Map<String, String> splitArgs(String args) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = args.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    // implement remaining HTTP actions here
    protected class RestRequest {
        // Accommodate two requests, one for all resources, another for a specific
        // resource
        private Pattern regExAllPattern = Pattern.compile("/" + tagName);
        private Pattern regExIdPattern = Pattern.compile("/" + tagName + "/([0-9]*)");

        private int id = -1;

        protected RestRequest(String pathInfo) throws ServletException {
            // regex parse pathInfo
            Matcher matcher;

            // Check for ID case first, since the All pattern would also match
            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                if (matcher.group(1).length() > 0) {
                    id = Integer.parseInt(matcher.group(1));
                }
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) {
                return;
            }

            throw new ServletException("Invalid URI");
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
