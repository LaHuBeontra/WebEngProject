package org.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	    String userName=request.getParameter("UserName");  
        String password=request.getParameter("Password");  
          
        LoginService loginService = new LoginService();  
          
        boolean status=loginService.validate(userName, password);  
          
        if(status){  
        	User user = loginService.getUserDetails(userName);
        	//add user to session variable
			request.getSession().setAttribute("user", user);
			
			//Redirect to Management page if user is admin
            if (user.isAdmin()) request.getRequestDispatcher("/ManagementService.java").forward(request, response);
            else request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
        }  
        else{  
            RequestDispatcher rd=request.getRequestDispatcher("LoginError.jsp");  
            rd.forward(request, response);  
        } 
	}

}
