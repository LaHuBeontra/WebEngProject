package org.management.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.LoginService;

/**
 * Servlet implementation class ToggleStatusServlet
 */
@WebServlet(urlPatterns = "/ToggleStatusServlet.java")
public class ToggleStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToggleStatusServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String userName        = request.getParameter("toggleUser");
		String currentUserName = (String) request.getSession().getAttribute("userName");
	    
		LoginService loginService = new LoginService();
		
	    //Check if a user is trying to change his own status
	    if (!userName.equals(currentUserName)) {
	    	//Change Status of user
	    	String status = loginService.getStatusFromUser(userName);
	        loginService.changeStatus(userName, status);
	        response.sendRedirect("/Phinapahu/ManagementServlet.java");
	    } else {
	    	request.setAttribute("managementError", "You can't change your own Status!");
	    	request.getRequestDispatcher("/ManagementServlet.java").forward(request, response);
	    }
	}

}
