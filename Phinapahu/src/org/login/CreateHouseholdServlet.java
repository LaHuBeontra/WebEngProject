package org.login;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class CreateHouseholdServlet
 */
@WebServlet("/CreateHouseholdServlet")
public class CreateHouseholdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateHouseholdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
 
        out.print("<html><body>");
        out.print("<h1> Your Order...</h1>");
        out.println("<table border=\"1\" cellpadding = \"5\"" + 
                " cellspacing = \"5\">");
        out.println("<tr> <th>Parameter Name</th>" + 
                "<th>Parameter Value</th></tr>");
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
            String paramName = entry.getKey();
            if(!paramName.equals("HouseholdName")&&!paramName.equals("login")) {
            out.print("<tr><td>" + paramName + "</td><td>");
            String[] paramValues = entry.getValue();
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                    out.println("<b>No Value</b>");
                else
                    out.println(paramValue);
            } else {
                out.println("<ul>");
                for (int i = 0; i < paramValues.length; i++) {
                    out.println("<li>" + paramValues[i] + "</li>");
                }
                out.println("</ul>");
            }
            out.print("</td></tr>");
        }}
        out.println("</table></body></html>");		
	}

}
