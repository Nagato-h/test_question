

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditConfirm
 */
@WebServlet("/EditConfirm")
public class EditConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
			// セッションから question_id を取得
			String question_id = (String)session.getAttribute("question_id");
			
			//問題編集画面で入力された値をパラメーターで取得
			String edit_question = request.getParameter("question");
			String[] edit_answers = request.getParameterValues("answers");
			
			//セッションへの書き込み
			session.setAttribute("question_id", question_id);
			session.setAttribute("edit_question", edit_question);
			session.setAttribute("edit_answers", edit_answers);
			
			if (edit_question.equals("")) {
				// 問題未入力時の処理
				request.setAttribute("error_1", "<span class=\"material-symbols-outlined\">error</span> 問題が未入力です");
				RequestDispatcher dispatch = request.getRequestDispatcher("edit.jsp");
				dispatch.forward(request, response);
				return;
			} else if (edit_question.length() > 500) {
				//問題の文字数オーバー時の処理
				request.setAttribute("error_1", "<span class=\"material-symbols-outlined\">error</span> 問題の文字数上限を超えています(500字以内)");
				RequestDispatcher dispatch = request.getRequestDispatcher("edit.jsp");
				dispatch.forward(request, response);
				return;
			}
			for(int i = 0; i < edit_answers.length; i++) {
				if (edit_answers[i].length() > 200) {
					//回答の文字数オーバー時の処理
					request.setAttribute("error_2", "<span class=\"material-symbols-outlined\">error</span> 回答の文字数上限を超えています(200字以内)");
					RequestDispatcher dispatch = request.getRequestDispatcher("edit.jsp");
					dispatch.forward(request, response);
					return;
				} else if(edit_answers[i].equals("")) {
					// 未入力時の処理
					request.setAttribute("error_2", "<span class=\"material-symbols-outlined\">error</span> 未入力の回答があります");
					RequestDispatcher dispatch = request.getRequestDispatcher("edit.jsp");
					dispatch.forward(request, response);
					return;
				}
			}
			
			//問題・回答が空でなく文字数制限にかからなかったら確認画面に遷移
			RequestDispatcher dispatch = request.getRequestDispatcher("edit_confirm.jsp");
			dispatch.forward(request, response);
			return;
		}
	}
}
