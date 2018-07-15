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
 * Servlet implementation class Vote
 */
@WebServlet("/Vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Vote() {
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
		String userName = (String) request.getSession().getAttribute("userName");
		LoginService loginService = new LoginService();
		request.setAttribute("userName", userName);
		request.setAttribute("loginService", loginService);
		
		String vote = request.getParameter("vote");
		try {
			EssenBean essen = new EssenBean(vote);
			String voteMessage = essen.vote(userName);
			request.setAttribute("voteMessage", voteMessage);
			RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
