package org.management.actions;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.EmailService;
import org.login.LoginService;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet(urlPatterns = "/AddMemberServlet.java")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String householdName = (String) request.getSession().getAttribute("householdName");
	    String email         = request.getParameter("MemberEmail");
	    String[] emails      = new String[1];
	    emails[0]            = email;
	    
	    LoginService loginService = new LoginService();
	    
	    String password = loginService.getHouseholdPassword(householdName);
	    
	    if (email.isEmpty()) {
			request.setAttribute("emailError", "Please enter recipient's email adress!");
			RequestDispatcher rd = request.getRequestDispatcher("AddMember.jsp");
			rd.forward(request, response);
		} else {
			if (loginService.areEmailsValid(emails)) {
				EmailService emailService = new EmailService();
				emailService.sendInvitationMail(emails, "noreply.phinapahu@gmail.com",
						request.getParameter("invitationText"), password);
				
				request.setAttribute("email", email);
				request.getRequestDispatcher("/SuccessfullMail.jsp").forward(request, response);
			}
			request.setAttribute("emailError", "Please check that all entered email addresses are valid");
		}
	}

}
