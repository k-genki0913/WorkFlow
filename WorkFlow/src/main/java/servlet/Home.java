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
import model.Ringisho;



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
		//今後、jspファイルで表示する一覧を出力する処理を記載予定
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("Account");
		List<Ringisho> list = new ArrayList<>();
		int positionID = account.getPosition();
		if(positionID == 1 || positionID == 2) {
			HomeListDAO homeListDAO = new HomeListDAO();
			list = homeListDAO.requestList(account);
			request.setAttribute("RequestList", list);
		}
		
		
		
		//ホーム画面を表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
		
	}

}
