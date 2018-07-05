package org.Essen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

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
		doGet(request, response);
		String dateString = request.getParameter("EssenDate");
		String essenString = request.getParameter("Essen");
		if (dateString != "") {
			if (essenString != "") {
				essenString = essenString.replace(" ", "_");
				try {
					EssenBean essen = new EssenBean(dateString, essenString);
					if(essen.validate()){
						essen.saveThis();
					}					
				} catch (Exception e) {
					System.out.println("test");
					System.err.println(e.getMessage());
				}

			} else {
				System.out.println("Das essen ist leer");
			}
		} else

		{
			System.out.println("Das Datum ist leer");
		}

		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}
