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

import dao.AllUserDAO;
import model.Account;
import model.DepartmentConvert;
import model.PositionConvert;

/**
 * Servlet implementation class AdminWindow
 */
@WebServlet("/AdminWindow")
public class AdminWindow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ユーザー一覧を格納するList
		List<Account> userList = new ArrayList<>();
				
		//ユーザー一覧を取得する
		AllUserDAO allUserDAO = new AllUserDAO();
		userList = allUserDAO.getAllUser();
				
		//adminWindow.jspへユーザー一覧をリクエストスコープで送る
		//その画面上で毎回更新されたらいいので、リクエストスコープに保存する
		request.setAttribute("AllUserList", userList);
		
		//adminWindow.jspでAccounインスタンスの部署IDを部署名に変換するために
		//DepartmentConvertを生成し、リクエストスコープへ保存
		DepartmentConvert dc = new DepartmentConvert();
		request.setAttribute("Dc", dc);
		
		//上記と同じ方法で役職名を変換する
		PositionConvert pc = new PositionConvert();
		request.setAttribute("Pc", pc);
				
		//adminWindow.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminWindow.jsp");
		dispatcher.forward(request, response);
	}

}
