package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserRegistReDAO;
import model.NewUserRegistCheck;

/**
 * Servlet implementation class ReUserRegist
 */
@WebServlet("/ReUserRegist")
public class ReUserRegist extends HttpServlet {
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
		String documentName = request.getParameter(request.getParameter("DocumentName"));
		String registName = request.getParameter("name");
		int departmentID = Integer.parseInt(request.getParameter("department"));
		int positionID = Integer.parseInt(request.getParameter("position"));
		
		NewUserRegistCheck newUserRegistCheck = new NewUserRegistCheck();
		UserRegistReDAO urrDAO = new UserRegistReDAO();
		if(!(newUserRegistCheck.check(registName))) {
			request.setAttribute("errMsg", "氏名を入力して下さい");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/remandUserRegist.jsp");
			dispatcher.forward(request, response);
		} else if(urrDAO.reapplicate(formID, registName, departmentID, positionID)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/requestSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errMsg", "登録時にエラーが発生しました。再度申請してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/remandUserRegist.jsp");
			dispatcher.forward(request, response);
		}
	}

}
