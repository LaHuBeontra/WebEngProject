package org.login;

import java.io.IOException;
import java.io.PrintWriter;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName     = (String) request.getSession().getAttribute("userName");
		String userPassword = (String) request.getSession().getAttribute("userPassword");
		
		//Generate random password
		LoginService loginService = new LoginService();
		String householdPassword  = loginService.generatePassword();
		
		//Store Household to FileSystem
		Household household = new Household();
		String householdName = request.getParameter("HouseholdName");
		household.setHouseholdName(request.getParameter("HouseholdName"));
		household.setPassword(householdPassword);
		
		//Check if Household already exists
		if (loginService.listContainsHousehold(request.getParameter("HouseholdName"))) {
			request.setAttribute("createHouseholdError", "Household already exists, please enter a different name!");
			RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
			rd.forward(request, response);
		} else {
			
		//Get parameters
		Map<String, String[]> map = request.getParameterMap();
		int parametersCount = map.size();
		String[] emails = new String[parametersCount - 3];
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print("<html><body>");
		out.print("<h1> Email successfully sent to...</h1>");
		out.println("<table border=\"1\" cellpadding = \"5\"" + " cellspacing = \"5\">");
		// out.println("<tr> <th>Parameter Name</th>" +
		// "<th>Parameter Value</th></tr>");
		Set set = map.entrySet();
		Iterator it = set.iterator();
		boolean isEmailEntered = false;
		int count = 0;
		while (it.hasNext()) {
			Map.Entry<String, String[]> entry = (Entry<String, String[]>) it.next();
			String paramName = entry.getKey();
			if (paramName.startsWith("Email")) {
				isEmailEntered = true;
				out.print("<tr>");
				String[] paramValues = entry.getValue();
				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					if (paramValue.length() == 0)
						out.println("<b>No Value</b>");
					else {
						out.println(paramValue);
						emails[count++] = paramValue;

					}
				} else {
					out.println("<ul>");
					for (int i = 0; i < paramValues.length; i++) {
						out.println("<li>" + paramValues[i] + "</li>");
					}
					out.println("</ul>");
				}
				out.print("</td></tr>");
			}
		}
		out.println("</table>");
		out.println(householdPassword);
		// out.println(user.getUserName());
		out.println("</body></html>");
		
		//Check if email is entered
		if (!isEmailEntered) {
			request.setAttribute("emailMissing", "Please enter recipient's email adresses");
			RequestDispatcher rd = request.getRequestDispatcher("CreateHousehold.jsp");
			rd.forward(request, response);
		} else {
			if (loginService.areEmailsValid(emails)) {

				loginService.createHousehold(householdName, userName, userPassword, householdPassword);
				
				EmailService emailService = new EmailService();
				emailService.sendInvitationMail(emails, "noreply.phinapahu@gmail.com",
						request.getParameter("invitationText"), householdPassword);
			}
			request.setAttribute("emailError", "Please check that all entered email addresses are valid");
		}
		
		}
	}

}
