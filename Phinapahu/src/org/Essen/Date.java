package org.Essen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.login.FileService;

/**
 * Servlet implementation class Date
 */
@WebServlet("/Date")
public class Date extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public Date() {
		super();
	}

	/**
	 * @return
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
		String dateString = request.getParameter("date");
		String userName = (String) request.getSession().getAttribute("userName");
		FileService fileService = new FileService();
		request.setAttribute("userName", userName);
		request.setAttribute("fileService", fileService);
		
		if (dateString.length() > 0) {

			SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
			try {
				sdfToDate.parse(dateString);
				
				File dates = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\dates.txt");

				try (PrintWriter pw = new PrintWriter(new FileWriter(dates, true))) {
					pw.println(dateString);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (ParseException ex2) {
				ex2.printStackTrace();
			}
			
			request.setAttribute("essenMessage", "Date added!");
			RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("essenMessage", "You haven't selected a date!");
			RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
			rd.forward(request, response);
		}
	}

}
