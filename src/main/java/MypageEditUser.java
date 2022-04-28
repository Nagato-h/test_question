

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test_question.UsersDao;

/**
 * Servlet implementation class MypageEditUser
 */
@WebServlet("/MypageEditUser")
public class MypageEditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageEditUser() {
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
			//セッションからID・パスワードを取得
			int user_id = (int)session.getAttribute("user_id");
			String password = (String)session.getAttribute("password");
			//ユーザー名編集画面で入力された値をパラメーターで取得
			String new_user_name = request.getParameter("new_user_name");
			String user_password = request.getParameter("user_password");
			//セッションへの書き込み
			session.setAttribute("edit_user_name", new_user_name);
			
			if (new_user_name.equals("")) {
				// ユーザー名未入力時の処理
				request.setAttribute("error_NewUserName", "<span class=\"material-symbols-outlined\">error</span> ユーザー名が未入力です");
				RequestDispatcher dispatch = request.getRequestDispatcher("mypage_edit.jsp");
				dispatch.forward(request, response);
				return;
			} else if (new_user_name.length() > 15) {
				// ユーザー名の文字数オーバー時の処理
				request.setAttribute("error_NewUserName", "<span class=\"material-symbols-outlined\">error</span> ユーザー名の文字数上限を超えています(15字以内)");
				RequestDispatcher dispatch = request.getRequestDispatcher("mypage_edit.jsp");
				dispatch.forward(request, response);
				return;
			}
			try {
				UsersDao u_dao = null;
				u_dao = new UsersDao();
				//パスワードが一致した場合
				if (password.equals(user_password)) {
					//ユーザー名を更新するメソッドの呼び出し
					u_dao.update_user_name(user_id, new_user_name);
					session.setAttribute("user_name", new_user_name);
					request.setAttribute("edit_completed_user", "<span class=\"material-symbols-outlined\">check_circle</span> ユーザー名を変更しました");
					RequestDispatcher dispatch = request.getRequestDispatcher("mypage_edit.jsp");
					dispatch.forward(request, response);
					return;
				} else {
					request.setAttribute("error_user_password", "<span class=\"material-symbols-outlined\">error</span> パスワードが一致しません");
					RequestDispatcher dispatch = request.getRequestDispatcher("mypage_edit.jsp");
					dispatch.forward(request, response);
					return;
				}
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}
	}

}
