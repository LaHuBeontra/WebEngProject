package org.management;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.LoginService;

/**
 * Servlet implementation class ManagementServlet
 */
@WebServlet(urlPatterns = "/ManagementServlet.java")
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("userName");
		
		LoginService loginService = new LoginService();
		
		String householdName = loginService.getUsersHouseholdName(userName);
		
		request.setAttribute("users", loginService.getUsersOfHousehold(householdName));
		request.setAttribute("loginService", loginService);
		request.getSession().setAttribute("userName", userName);
		request.getSession().setAttribute("householdName", householdName);
		
		request.getServletContext().getRequestDispatcher("/Management.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
