package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HomeListDAO;
import model.Account;
import model.HomeDocument;


/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//documentListメソッドにdepartmentID、positionIDを渡すために、アカウント情報を生成
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("Account");
		
		//アカウントの役職、部署ごとに表示される承認待ち書類を一覧から取得
		List<HomeDocument> list = new ArrayList<>();
		HomeListDAO homeListDAO = new HomeListDAO();
		list = homeListDAO.documentList(account.getDepartment(), account.getPosition());
		
		//取得したものをリクエストセッションへ保存
		request.setAttribute("HomeList", list);
		
		//ホーム画面を表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
		
	}

}
