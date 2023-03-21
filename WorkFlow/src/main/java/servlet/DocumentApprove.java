package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RingishoApproveDAO;
import model.ApproveRingisho;
import model.DocumentCheck;
import model.Ringisho;


/**
 * Servlet implementation class DocumentApprove
 */
@WebServlet("/DocumentApprove")
public class DocumentApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int formID = Integer.parseInt(request.getParameter("formID"));
		String documentTable = "RINGISHO";
		
		System.out.println(formID);
		
		System.out.println(documentTable);
		
		if(documentTable.equals("RINGISHO")) {
			ApproveRingisho aRingisho = new ApproveRingisho();
			Ringisho ringisho = new Ringisho();
			ringisho = aRingisho.getRingisho(formID);
			request.setAttribute("Ringisho", ringisho);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approveRingisho.jsp");
			dispatcher.forward(request, response);
		} else if(documentTable.equals("USERREGIST")) {
			System.out.println("ユーザー登録承認画面へ移動するところまで来ている");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int approveresult = Integer.parseInt(request.getParameter("result"));
		String approver = request.getParameter("approver");
		int formID = Integer.parseInt(request.getParameter("formID"));
		
		DocumentCheck documentCheck = new DocumentCheck();
		String documentTable = documentCheck.check(formID);
		
		if(documentTable.equals("RINGISHO")) {
			ApproveRingisho aRingisho = new ApproveRingisho();
			Ringisho ringisho = new Ringisho();
			ringisho = aRingisho.getRingisho(formID);
			if(approveresult == 1) {
				if(ringisho.getSituation() == 2) {
					ringisho.setSituation(1);
					ringisho.setMApprover(approver);
				} else if(ringisho.getSituation() == 1) {
					ringisho.setSituation(0);
					ringisho.setGMApprover(approver);
				}
			} else if(approveresult == -1) {
				if(ringisho.getSituation() == 1) {
					ringisho.setSituation(2);
				} else if(ringisho.getSituation() == 2) {
					ringisho.setSituation(99);
				}
			}
			RingishoApproveDAO rADAO = new RingishoApproveDAO();
			boolean regist = rADAO.approveRingisho(approveresult, ringisho);
			System.out.println(regist);
			if(regist) {
				response.sendRedirect("Home");
			} else {
				request.setAttribute("registErrMsg", "再度登録してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approveRingisho.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
