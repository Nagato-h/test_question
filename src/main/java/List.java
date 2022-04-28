

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test_question.CorrectAnswersBean;
import test_question.CorrectAnswersDao;
import test_question.QuestionsBean;
import test_question.QuestionsDao;

/**
 * Servlet implementation class List
 */
@WebServlet("/List")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// セッションの開始
		HttpSession session = request.getSession(false);
		if (session == null){
			// エラーページに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
			return;
		} else {
			try {
				QuestionsDao q_dao = new QuestionsDao();
				ArrayList<QuestionsBean> q_list = q_dao.findAll();
				request.setAttribute("question_list", q_list);
				CorrectAnswersDao a_dao = new CorrectAnswersDao();
				ArrayList<CorrectAnswersBean> a_list = a_dao.findAll();
				request.setAttribute("answer_list", a_list);
				// 編集時の問題・回答セッションを削除
				//session.removeAttribute("edit_question");
				//session.removeAttribute("edit_answers");
				RequestDispatcher dispatch = request.getRequestDispatcher("list.jsp");
				dispatch.forward(request, response);
				return;
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
