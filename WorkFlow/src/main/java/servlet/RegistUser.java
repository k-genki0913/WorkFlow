package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegistUserDAO;
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
		
		//registUserFormへ表示させるメッセージの確認。
		String resultMsg = checkErrMsg(checkResult);
		
		//Msgが""ならアカウントチェックは全てtrue
		//登録に進み、真偽値によってメッセージを格納
		if(resultMsg.length() == 0) {
			RegistUserDAO registUserDAO = new RegistUserDAO();
			boolean registResult = registUserDAO.registUser(account);
			if(registResult) {
				resultMsg += "登録完了";
			} else {
				resultMsg += "登録できませんでした";
			}
		} 
		
		request.setAttribute("ResultMsg", resultMsg);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registUserForm.jsp");
		dispatcher.forward(request, response);
		
		
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
