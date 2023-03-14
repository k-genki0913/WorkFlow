package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.AccountCheck;

/**
 * Servlet implementation class RegistUser
 */
@WebServlet("/RegistUser")
public class RegistUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registUserForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//登録データをリクエストから取得
		//部署と役職は数字へ変換
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("ID");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sDepartment = request.getParameter("department");
		int department = Integer.parseInt(sDepartment);
		String sPosition = request.getParameter("position");
		int position = Integer.parseInt(sPosition);
		
		//アカウントインスタンスの生成
		Account account = new Account(userId, password, name, department, position);
		
		//アカウント情報のチェック
		AccountCheck accountCheck = new AccountCheck();
		boolean[] checkResult = accountCheck.execute(account);
		for(boolean result: checkResult) {
			System.out.println(result);
		}
		
		//エラーメッセージの確認。
		String errMsg = checkErrMsg(checkResult);
		
		//errMsgがnullなら登録。エラーメッセージがあるならregistUserFormへforward
		//エラーメッセージはリクエストスコープに保存(このリクエストが終わった後に必要なくなるためリクエストスコープを選択)
		if(errMsg.length() == 0) {
			//アカウント登録依頼(DBへ)
			System.out.println("登録まで進んでいる");
		} else {
			request.setAttribute("ErrorMsg", errMsg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registUserForm.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	//エラーメッセージの確認メソッド
	public String checkErrMsg(boolean[] checkResult) {
		String errMsg = "";
		for(int i = 0; i < checkResult.length; i++) {
			boolean result = checkResult[i];
			if(result == false){
				switch(i) {
				case 0:
					errMsg += "ユーザーIDに誤りがあります。";
					break;
				case 1:
					errMsg += "パスワードに誤りがあります。";
					break;
				case 2:
					errMsg += "名前を入力してください";
					break;
				}
			}
		}
		return errMsg;
	}

}
