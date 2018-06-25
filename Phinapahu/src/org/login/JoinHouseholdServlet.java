package org.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinHouseholdServlet
 */
@WebServlet("/JoinHouseholdServlet")
public class JoinHouseholdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinHouseholdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String householdPassword  = request.getParameter("HouseholdPassword");
		String userName           = (String) request.getSession().getAttribute("userName");
		String userPassword       = (String) request.getSession().getAttribute("userPassword");
		LoginService loginService = new LoginService();
		
		//Check if there is a household with this password
		if (loginService.passwordFitsHousehold(householdPassword)) {
			
			loginService.joinHousehold(userName, userPassword, householdPassword);
		    
		    request.getSession().setAttribute("userName", userName);
		    request.getSession().setAttribute("householdName", loginService.getUsersHouseholdName(userName));
			RequestDispatcher rd = request.getRequestDispatcher("RegisterSuccess.jsp");
			rd.forward(request, response);
			
		} else {
			request.setAttribute("JoinHouseholdError", "This household does not exist, please check your password again!");
			RequestDispatcher rd = request.getRequestDispatcher("JoinHousehold.jsp");
            rd.forward(request, response);
		}
	}
}
