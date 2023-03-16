package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/logout.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ログアウト画面の入力値を取得
		//「１」はログアウト、「２」はホーム画面へ戻る
		request.setCharacterEncoding("UTF-8");
		int logout = Integer.parseInt(request.getParameter("logout"));
		
		if(logout == 1) {
			//セッションスコープに保存されている情報を削除
			//Accountインスタンスが削除される
			HttpSession session = request.getSession();
			session.invalidate();
			
			//ログイン画面へ戻る
			response.sendRedirect("Login");
		} else if(logout == 2) {
			response.sendRedirect("Home");
		}
	}
}
