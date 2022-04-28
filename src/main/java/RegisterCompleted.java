

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test_question.CorrectAnswersDao;
import test_question.QuestionsDao;

/**
 * Servlet implementation class RegisterCompleted
 */
@WebServlet("/RegisterCompleted")
public class RegisterCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCompleted() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// セッションの開始
		HttpSession session = request.getSession(false);
		if (session == null){
			// エラーページに遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
			return;
		} else {
			//問題登録画面で入力された値をパラメーターで取得
			String question = (String)session.getAttribute("question");
			String[] answers = (String[])session.getAttribute("answers");
			QuestionsDao q_dao = null;
			CorrectAnswersDao a_dao = null;
			try {
				q_dao = new QuestionsDao();
				a_dao = new CorrectAnswersDao();
				//問題登録するメソッドの呼び出し
				int q_id = q_dao.insert_question_info(question);
				a_dao.insert_answer_info(q_id, answers);
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			//問題・答え一覧に遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("register_completed.jsp");
			dispatch.forward(request, response);
		}
	}
}
