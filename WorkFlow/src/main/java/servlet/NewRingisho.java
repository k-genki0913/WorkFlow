package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RingishoNewDAO;
import model.NewRingishoCheck;

/**
 * Servlet implementation class NewRingisho
 */
@WebServlet("/NewRingisho")
public class NewRingisho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ringisho.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String applicantName = request.getParameter("applicantName");
		int departmentID = Integer.parseInt(request.getParameter("departmentID"));
		String contents = request.getParameter("contents");
		
		NewRingishoCheck nrCheck = new NewRingishoCheck();
		RingishoNewDAO rsNew = new RingishoNewDAO();
		if(!(nrCheck.check(contents))) {
			request.setAttribute("errMsg", "申請内容を入力して下さい");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ringisho.jsp");
			dispatcher.forward(request, response);
		} else if(rsNew.registRingisho(applicantName, departmentID, contents)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/requestSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errMsg", "登録時にエラーが発生しました。再度申請してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ringisho.jsp");
			dispatcher.forward(request, response);
		}
	}

}
