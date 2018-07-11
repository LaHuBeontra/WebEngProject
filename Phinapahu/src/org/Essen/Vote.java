package org.Essen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String vote = request.getParameter("vote");
		// log("vote: " + vote);
		try {
			EssenBean essen = new EssenBean(vote);
			String voteMessage = essen.vote((String) request.getSession().getAttribute("userName"));
			request.setAttribute("voteMessage", voteMessage);
			RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// Aus dem essen und dem datum das essen finden und voten
		/*RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
		*/
	}

}
