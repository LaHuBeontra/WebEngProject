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
		String HouseholdPassword=request.getParameter("HouseholdPassword");
		User user = (User)request.getSession().getAttribute("user");
		//check if there is a household with this password
		LoginService loginService = new LoginService();
		boolean householdExists = loginService.checkHouseholdExists(HouseholdPassword);		
		 
		if(householdExists) {
			//set household of this user
			user.setHouseholdName(loginService.getHouseholdDetail(HouseholdPassword));
			//set user.admin to false
			user.setAdmin(false);
			//TODO store user in database
			
			request.getSession().setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterSuccess.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("JoinHousehold.jsp"); 
			request.setAttribute("JoinHouseholdError", "Please check that you entered the correct password");
            rd.forward(request, response);
		}
			
		
		
		
	}

}
