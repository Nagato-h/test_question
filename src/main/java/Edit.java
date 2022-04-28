

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
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// セッションの開始
		HttpSession session = request.getSession(false);
		if (session == null){
			// エラーページに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
			return;
		} else {
			try {
				//パラメータから question_id を取得
				String question_id = request.getParameter("question_id");
				session.setAttribute("question_id", question_id);
				
				QuestionsDao q_dao = new QuestionsDao();
				QuestionsBean edit_q = q_dao.find(Integer.parseInt(question_id));
				request.setAttribute("edit_question", edit_q);
				CorrectAnswersDao a_dao = new CorrectAnswersDao();
				ArrayList<CorrectAnswersBean> edit_a = a_dao.edit_find(Integer.parseInt(question_id));
				request.setAttribute("edit_answer", edit_a);
				RequestDispatcher dispatch = request.getRequestDispatcher("edit.jsp");
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
