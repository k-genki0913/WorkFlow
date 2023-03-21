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

import dao.ApprovedListDAO;
import model.Account;
import model.HomeDocument;

/**
 * Servlet implementation class ApprovedList
 */
@WebServlet("/ApprovedList")
public class ApprovedList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("Account");
		
		List<HomeDocument> list = new ArrayList<>();
		ApprovedListDAO alDAO = new ApprovedListDAO();
		list = alDAO.getApprovedList(account.getId());
		
		//取得したものをリクエストへ保存
		request.setAttribute("ApprovedList", list);
		
		//承認済み一覧を表示へ
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approvedList.jsp");
		dispatcher.forward(request, response);
	}

}
