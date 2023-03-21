package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewUserRegistDAO;
import model.NewUserRegistCheck;

/**
 * Servlet implementation class NewUserRegist
 */
@WebServlet("/NewUserRegist")
public class NewUserRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String applicantName = request.getParameter("applicantName");
		int applicantDepartment = Integer.parseInt(request.getParameter("applicantDepartment"));
		String registName = request.getParameter("name");
		int registDepartment = Integer.parseInt(request.getParameter("department"));
		int registPosition = Integer.parseInt(request.getParameter("position"));
		
		NewUserRegistCheck newUserRegistCheck = new NewUserRegistCheck();
		NewUserRegistDAO newUserRegistDAO = new NewUserRegistDAO();
		if(!(newUserRegistCheck.check(registName))) {
			request.setAttribute("errMsg", "氏名を入力してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegist.jsp");
			dispatcher.forward(request, response);
		} else if(newUserRegistDAO.regist(applicantName, applicantDepartment, registName, registDepartment, registPosition)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/requestSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errMsg", "登録時にエラーが発生しました。再度申請してください");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegist.jsp");
			dispatcher.forward(request, response);
		}
	}

}
