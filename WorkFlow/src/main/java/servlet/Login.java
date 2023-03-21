package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDAO;
import model.Account;
import model.DepartmentConvert;
import model.LoginCheck;
import model.PositionConvert;
import model.UserIDConvert;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//入力されたID、パスワードを取得
		request.setCharacterEncoding("UTF-8");
		String inputID = request.getParameter("userId");
		String inputPassword = request.getParameter("password");
		
		//Accountインスタンスの生成
		LoginDAO loginDAO = new LoginDAO();
		Account account = loginDAO.getAccount(inputID);
		
		//Accountインスタンスがnull = ログイン失敗
		LoginCheck loginCheck = new LoginCheck();
		String msg = loginCheck.loginCheck(account, inputPassword);
		
		if(msg.length() == 0) {
			//パスワードも正しければ、accountインスタンスをセッションスコープへ保存
			HttpSession session = request.getSession();
			session.setAttribute("Account", account);
			
			//ログイン時に多くの画面で利用するためでAccounインスタンスの部署IDを部署名に変換するために
			//DepartmentConvertを生成し、セッションスコープへ保存
			DepartmentConvert dc = new DepartmentConvert();
			session.setAttribute("Dc", dc);
					
			//上記と同じ方法で役職名を変換する
			PositionConvert pc = new PositionConvert();
			session.setAttribute("Pc", pc);
			
			//同じ方法でユーザー名を変換する
			UserIDConvert uic = new UserIDConvert();
			session.setAttribute("Uic", uic);
			
			//ホーム画面へリダイレクト
			response.sendRedirect("Home");
		} else {
			request.setAttribute("errMsg", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
