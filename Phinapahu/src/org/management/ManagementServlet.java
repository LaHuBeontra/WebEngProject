package org.management;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.User;
import org.login.UserList;

/**
 * Servlet implementation class ManagementServlet
 */
@WebServlet(urlPatterns = "/ManagementServlet.java")
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserList userList = new UserList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		request.setAttribute("users", userList.getUsers());
		request.setAttribute("name", user.getUserName());
		
		request.getServletContext().getRequestDispatcher("/Management.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
