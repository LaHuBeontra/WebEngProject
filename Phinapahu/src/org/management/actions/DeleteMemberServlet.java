package org.management.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.FileService;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet(urlPatterns = "/DeleteMemberServlet.java")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName        = request.getParameter("deleteUser");
		String currentUserName = (String) request.getSession().getAttribute("userName");
		
		FileService fileService = new FileService();
		
	    //Check if a user is trying to remove himself
	    if (!userName.equals(currentUserName)) {
	    	//Remove user
	        fileService.deleteUser(userName);
	        response.sendRedirect("/Phinapahu/ManagementServlet.java");
	    } else {
	    	request.setAttribute("managementError", "You can't delete yourself!");
	    	request.getRequestDispatcher("/ManagementServlet.java").forward(request, response);
	    }
	}

}
