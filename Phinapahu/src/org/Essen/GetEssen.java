package org.Essen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
 * Servlet implementation class GetEssen
 */
@WebServlet("/GetEssen")
public class GetEssen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEssen() {
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
		
		Set<EssenBean> getEssenSet = new TreeSet<EssenBean>();
		File essen = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\essen.txt");
		if (essen.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(essen))) {
				while (br.ready()) {
					try {
						EssenBean test = new EssenBean(br.readLine());
						getEssenSet.add(test);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}

		}
		request.setAttribute("getEssenSet", getEssenSet);

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
