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

import org.login.FileService;

/**
 * Servlet implementation class GetDates
 */
@WebServlet("/GetDates")
public class GetDates extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetDates() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("userName");
		FileService fileService = new FileService();
		request.setAttribute("userName", userName);
		request.setAttribute("fileService", fileService);
		
		Set<String> getDateSet = new TreeSet<String>();
		File dates = new File("..\\git\\WebEngProject\\Phinapahu\\WebContent\\FileEssen\\dates.txt");
		if (dates.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(dates))) {
				String line;
				if ((line = br.readLine()) == null) {
					request.setAttribute("voteMessage", "There are no dates yet!");
		        	RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		        	rd.forward(request, response);
				} else {
				    while (line != null) {
					    getDateSet.add(line);
					    line = br.readLine();
				    }
				    
				    request.setAttribute("getDateSet", getDateSet);
					RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
					rd.forward(request, response);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("Essen.jsp");
		rd.forward(request, response);
	}

}
