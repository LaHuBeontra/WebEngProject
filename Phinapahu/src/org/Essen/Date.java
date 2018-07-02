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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.getWriter().append("Served at: ").append(request.getContextPath());
		log("datum request");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dateString = request.getParameter("Date");
		if (dateString.length() > 0) {

			SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
			try {
				sdfToDate.parse(dateString);
				log("Datum: " + dateString + " erfolgreich");

				File dates = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\dates.txt");
				//"..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\dates.txt"

				try (PrintWriter pw = new PrintWriter(new FileWriter(dates, true))) {
					pw.println(dateString);
				} catch (IOException e) {
					System.err.println("Fehler beim Schreiben: " + e.getMessage());
				}

			} catch (ParseException ex2) {
				log(dateString+": " +"Fehler bei der Datumsumwandlung");
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);

		// dateList.add(date);

	}

}
