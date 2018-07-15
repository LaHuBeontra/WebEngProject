package org.Essen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.LoginService;

/**
 * Servlet implementation class EssenErstellen
 */
@WebServlet("/EssenErstellen")
public class EssenErstellen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EssenErstellen() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		String userName = (String) request.getSession().getAttribute("userName");
		LoginService loginService = new LoginService();
		request.setAttribute("userName", userName);
		request.setAttribute("loginService", loginService);
		
		String dateString = request.getParameter("EssenDate");
		String essenString = request.getParameter("Essen");
		System.out.println("dateString: " + dateString + " essenString: " + essenString);
		String essenMessage = null;
		if (dateString != null && essenString != null) {
			if (dateString != "") {
				if (essenString != "") {
					if (!essenString.contains(";")) {
					    essenString = essenString.replace(" ", "_");
					    try {
						    EssenBean essen = new EssenBean(dateString, essenString);
						    if (essen.validate()) {
							    essen.saveThis();
							    essenMessage = "Meal added!";
						    }
					    } catch (Exception e) {
						    e.printStackTrace();
					    }
					} else {
						essenMessage = "Your meal can't contain a semicolon!";
					}

				} else {
					essenMessage = "You have to enter a meal!";
				}
			} else {
				essenMessage = "Please select a date!";
			}
		}
		request.setAttribute("essenMessage", essenMessage);
		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}