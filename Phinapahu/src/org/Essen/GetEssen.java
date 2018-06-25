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

import org.EssenBean;

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
		File dates = new File("dates.txt");
		if (dates.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(dates))) {
				while (br.ready())
					getEssenSet.add((EssenBean.parse(br.readLine())) );
			} catch (IOException e) {
				System.err.println("Fehler beim Lesen: " + e.getMessage());
			}
			
		}
		log("size of EssenList: " + getEssenSet.size());
		request.setAttribute("getEssenSet", getEssenSet);

		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}
