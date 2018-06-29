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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Set<EssenBean> getEssenSet = new TreeSet<EssenBean>();
		File essen = new File("Essen.txt");
		if (essen.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(essen))) {
				while (br.ready()) {
					log("reader ist am lesen");
					// log("essen wird versucht hinzuzufügen");
					try {
						EssenBean test = new EssenBean(br.readLine());
						log(test.getDate() + "-" + test.getEssen() + "-" + test.getVotes());
						 log("Wenn da die essensdaten standen wird es jetzt hinzugefügt");
						 getEssenSet.add(test);
						log("essen wurde zur liste hinzugefügt");
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
				log("reader ist fertig");
			} catch (Exception ee) {
				System.err.println(ee.getMessage());
			}

		}

		log("size of EssenList: " + getEssenSet.size());
		request.setAttribute("getEssenSet", getEssenSet);

		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}
