package org.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String userName  = request.getParameter("UserName"); 
		String email     = request.getParameter("Email");
        String password  = request.getParameter("Password");
        String household = request.getParameter("Household");
        
        LoginService loginService = new LoginService();  
          
        String error = loginService.checkData(userName, password, email, household);
        
        if (error != null && !error.isEmpty()) {
        	//User already exists/is invalid
        	request.setAttribute("registrationError", error);
        	RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
        	rd.forward(request, response);
        } else {
        	request.getSession().setAttribute("userName", userName);
        	request.getSession().setAttribute("userPassword", password);
        	if(household.equals("Create")) {
        		RequestDispatcher rd=request.getRequestDispatcher("CreateHousehold.jsp");  
                rd.forward(request, response);
        	}else {
        		RequestDispatcher rd=request.getRequestDispatcher("JoinHousehold.jsp");  
        		rd.forward(request, response);
        	}
        		
        }
        
        
	}

}
