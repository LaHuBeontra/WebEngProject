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
		Set<EssenBean> getEssenSet = new TreeSet<EssenBean>();
		//File essen = new File("C:/Users/phils/git/WebEngProject/Phinapahu/WebContent/FileEssen/essen.txt");
		File essen = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\essen.txt");
		if (essen.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(essen))) {
				while (br.ready()) {
					try {
						EssenBean test = new EssenBean(br.readLine());
						getEssenSet.add(test);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			} catch (Exception ee) {
				System.err.println(ee.getMessage());
			}

		}
		log("size of EssenList: " + getEssenSet.size());
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
		// TODO Auto-generated method stub
		doGet(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}
