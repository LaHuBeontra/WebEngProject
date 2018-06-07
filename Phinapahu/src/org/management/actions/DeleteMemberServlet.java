package org.management.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.User;
import org.login.UserList;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet(urlPatterns = "/DeleteMemberServlet.java")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserList userList = new UserList();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
	    user.setUserName(request.getParameter("deleteUser").toString());
	    
	    //Check if a user is trying to remove himself
	    User thisUser = (User)request.getSession().getAttribute("user");
	    if (!user.equals(thisUser)) {
	    	//Remove user
	        userList.removeUser(user);
	        response.sendRedirect("/Phinapahu/ManagementServlet.java");
	    } else {
	    	request.getRequestDispatcher("/ManagementActionError.jsp").forward(request, response);
	    }
	}

}
