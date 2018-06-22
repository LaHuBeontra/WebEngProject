package org.login;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class CreateHouseholdServlet
 */
@WebServlet("/CreateHouseholdServlet")
public class CreateHouseholdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateHouseholdServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// generate random password
		LoginService loginService = new LoginService();
		String password = loginService.generatePassword();

		// TODO store household to database
		Household household = new Household();
		household.setHouseholdName(request.getParameter("HouseholdName"));
		household.setPassword(password);

		// TODO store user to database
		User user = new User();
		if((User)request.getSession().getAttribute("user") == null) {
			request.setAttribute("registrationError", "Please register first");
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
			rd.forward(request, response);
		}else {
			user = (User) request.getSession().getAttribute("user");
			System.out.println(user.getUserName());
			user.setAdmin(true);
			user.setHouseholdName(household);
		}

		
			// get parameters
			Map<String, String[]> map = request.getParameterMap();
			int parametersCount = map.size();
			String[] emails = new String[parametersCount - 3];

			Set set = map.entrySet();
			Iterator it = set.iterator();
			boolean isEmailEntered = false;
			int count = 0;
			String[] paramValues = null;
			while (it.hasNext()) {
				Map.Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
				String paramName = entry.getKey();

				if (paramName.startsWith("Email")) {
					isEmailEntered = true;
					if (entry.getValue() != null) {
						paramValues = entry.getValue();
					}

					if (paramValues.length == 1) {
						String paramValue = paramValues[0];
						if (paramValue.length() == 0) {
							request.setAttribute("emailMissing", "not all emails were entered");
							RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
							rd.forward(request, response);}
						else {
							emails[count++] = paramValue;

						}
					}

				}
			}
			
			// check if email is entered
			if (!isEmailEntered) {
				request.setAttribute("emailMissing", "Please enter recipient's email adresses");
				RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
				rd.forward(request, response);
			} else {
				boolean valid = loginService.areEmailsValid(emails);
				System.out.println(valid);
				if (valid) {
					System.out.println("test");
					EmailService emailService = new EmailService();
					emailService.sendInvitationMail(emails, "noreply.phinapahu@gmail.com",
						request.getParameter("invitationText"), password);
					System.out.println(user.getUserName());
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("/ManagementServlet.java").forward(request, response);
					
//					RequestDispatcher rd = request.getRequestDispatcher("Management.jsp");
//					rd.forward(request, response);
					
				}else {
					request.setAttribute("emailMissing", "Please check that all entered email addresses are valid");
					RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
					rd.forward(request, response);
				}
			}

		
	}

}
