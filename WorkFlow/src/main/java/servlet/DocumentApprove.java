package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RingishoApproveDAO;
import dao.UserRegistApproveDAO;
import model.ApproveRingisho;
import model.ApproveUserRegist;
import model.Ringisho;
import model.UserRegist;


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
		String documentTable = request.getParameter("documentTable");
		
		System.out.println(formID);
		
		System.out.println(documentTable);
		
		if(documentTable.equals("RINGISHO")) {
			ApproveRingisho aRingisho = new ApproveRingisho();
			Ringisho ringisho = new Ringisho();
			ringisho = aRingisho.getRingisho(formID);
			request.setAttribute("Ringisho", ringisho);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approveRingisho.jsp");
			dispatcher.forward(request, response);
		} else if(documentTable.equals("UserRegist")) {
			ApproveUserRegist aUserRegist = new ApproveUserRegist();
			UserRegist userRegist = new UserRegist();
			userRegist = aUserRegist.getUserRegist(formID);
			request.setAttribute("UserRegist", userRegist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approveUserRegist.jsp");
			dispatcher.forward(request, response);
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
		String documentTable = request.getParameter("documentTable");
		
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
			if(regist) {
				response.sendRedirect("Home");
			} else {
				request.setAttribute("registErrMsg", "再度登録してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approveRingisho.jsp");
				dispatcher.forward(request, response);
			}
		} else if(documentTable.equals("UserRegist")) {
			ApproveUserRegist aUserRegist = new ApproveUserRegist();
			UserRegist userRegist = new UserRegist();
			userRegist = aUserRegist.getUserRegist(formID);
			if(approveresult == 1) {
				if(userRegist.getSituation() == 2) {
					userRegist.setSituation(1);
					userRegist.setMApprover(approver);
				} else if(userRegist.getSituation() == 1) {
					userRegist.setSituation(0);
					userRegist.setGMApprover(approver);
				}
			} else if(approveresult == -1) {
				if(userRegist.getSituation() == 1) {
					userRegist.setSituation(2);
				} else if(userRegist.getSituation() == 2) {
					userRegist.setSituation(99);
				}
			}
			UserRegistApproveDAO uraDAO = new UserRegistApproveDAO();
			boolean regist = uraDAO.approve(approveresult, userRegist);
			if(regist) {
				response.sendRedirect("Home");
			} else {
				request.setAttribute("registErrMsg", "再度登録してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/approveUserRegist.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
