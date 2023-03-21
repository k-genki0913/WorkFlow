package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RingishoReDAO;
import model.NewRingishoCheck;

/**
 * Servlet implementation class ReRingisho
 */
@WebServlet("/ReRingisho")
public class ReRingisho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int formID = Integer.parseInt(request.getParameter("formID"));
		String documentName = request.getParameter("documentName");
		String contents = request.getParameter("contents");
		
		NewRingishoCheck nrCheck = new NewRingishoCheck();
		RingishoReDAO rrDAO = new RingishoReDAO();
		if(!(nrCheck.check(contents))) {
			request.setAttribute("errMsg", "申請内容を入力して下さい");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/remandRingisho.jsp");
			dispatcher.forward(request, response);
		} else if(rrDAO.reapplicate(contents, formID)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/requestSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errMsg", "登録時にエラーが発生しました。再度申請してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/remandRingisho.jsp");
			dispatcher.forward(request, response);
		}
	}

}
