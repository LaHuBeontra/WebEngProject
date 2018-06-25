package org.login;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("userName");
		String userPassword = (String) request.getSession().getAttribute("userPassword");

		// Generate random password
		LoginService loginService = new LoginService();
		String householdPassword = loginService.generatePassword();

		// Store Household to FileSystem
		String householdName = request.getParameter("HouseholdName");

		// Check if Household already exists
		if (loginService.listContainsHousehold(request.getParameter("HouseholdName"))) {
			request.setAttribute("createHouseholdError", "Household already exists, please enter a different name!");
			RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
			rd.forward(request, response);
		} else {
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
							rd.forward(request, response);
						} else {
							emails[count++] = paramValue;

						}
					}

				}
			}
			// Check if email is entered
			if (!isEmailEntered) {
				request.setAttribute("emailMissing", "Please enter recipient's email adresses");
				RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
				rd.forward(request, response);
			} else {
				boolean valid = loginService.areEmailsValid(emails);
				if (valid) {
					loginService.createHousehold(householdName, userName, userPassword, householdPassword);
					EmailService emailService = new EmailService();
					emailService.sendInvitationMail(emails, "noreply.phinapahu@gmail.com",
							request.getParameter("invitationText"), householdPassword);
					request.getSession().setAttribute("userName", userName);
					request.getRequestDispatcher("/ManagementServlet.java").forward(request, response);

				} else {
					request.setAttribute("emailMissing", "Please check that all entered email addresses are valid");
					RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
					rd.forward(request, response);
				}
			}

		}

	}
}
