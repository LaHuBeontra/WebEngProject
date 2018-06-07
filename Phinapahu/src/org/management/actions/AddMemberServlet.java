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
 * Servlet implementation class AddMemberServlet
 */
@WebServlet(urlPatterns = "/AddMemberServlet.java")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserList userList = new UserList();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
	    user.setUserName(request.getParameter("toggleUser").toString());
	    User listUser = userList.getUser(user);
	    
	    //Check if a user is trying to change his own status
	    User thisUser = (User)request.getSession().getAttribute("user");
	    if (!user.equals(thisUser)) {
	    	//Change Status of user
	        if (listUser.isAdmin()) listUser.setAdmin(false);
	        else listUser.setAdmin(true);
	        response.sendRedirect("/Phinapahu/ManagementServlet.java");
	    } else {
	    	request.getRequestDispatcher("/ManagementActionError.jsp").forward(request, response);
	    }
	}

}