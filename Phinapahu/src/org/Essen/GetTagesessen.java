package org.Essen;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.LoginService;

/**
 * Servlet implementation class Tagesessen
 */
@WebServlet("/GetTagesessen")
public class GetTagesessen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetTagesessen() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("userName");
		LoginService loginService = new LoginService();
		request.setAttribute("userName", userName);
		request.setAttribute("loginService", loginService);
		
		Set<EssenBean> getTagesEssen = new TreeSet<EssenBean>();
		try {
			 getTagesEssen =  EssenBean.tagesessen();
			request.setAttribute("getTagesEssen", getTagesEssen);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}
