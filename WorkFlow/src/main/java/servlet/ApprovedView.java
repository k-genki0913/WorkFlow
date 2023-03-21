package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DocumentCheck;
import model.ResultRingisho;
import model.Ringisho;

/**
 * Servlet implementation class ApprovedView
 */
@WebServlet("/ApprovedView")
public class ApprovedView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int formID = Integer.parseInt(request.getParameter("formID"));
		
		DocumentCheck documentCheck = new DocumentCheck();
		String documentTable = documentCheck.check(formID);
		
		if(documentTable.equals("RINGISHO")) {
			ResultRingisho rRingisho = new ResultRingisho();
			Ringisho ringisho = new Ringisho();
			ringisho = rRingisho.getRingisho(formID);
			request.setAttribute("Ringisho", ringisho);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/resultRingisho.jsp");
			dispatcher.forward(request, response);
		}
	}

}
