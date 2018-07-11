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
 * Servlet implementation class GetDates
 */
@WebServlet("/GetDates")
public class GetDates extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet() List<String> dateList = new
	 *      ArrayList<String>();
	 */

	public GetDates() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Set<String> getDateSet = new TreeSet<String>();
		//File dates = new File("C:/Users/phils/git/WebEngProject/Phinapahu/WebContent/FileEssen/dates.txt");
		File dates = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\dates.txt");
		if (dates.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(dates))) {
				while (br.ready())
					getDateSet.add(br.readLine());
			} catch (IOException e) {
				System.err.println("Fehler beim Lesen: " + e.getMessage());
			}
		}
		log("size of DateList: " + getDateSet.size());
		request.setAttribute("getDateSet", getDateSet);

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// List<String> getDateList = new ArrayList<String>();
	/*	Set<String> getDateSet = new TreeSet<String>();
		File dates = new File("dates.txt");
		if (dates.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(dates))) {
				while (br.ready())
					getDateSet.add(br.readLine());
			} catch (IOException e) {
				System.err.println("Fehler beim Lesen: " + e.getMessage());
			}
		}
		log("size of DateList: " + getDateSet.size());
		request.setAttribute("getDateSet", getDateSet);
		*/

		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}
