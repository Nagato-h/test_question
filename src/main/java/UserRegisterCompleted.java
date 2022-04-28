

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
 * Servlet implementation class UserRegisterCompleted
 */
@WebServlet("/UserRegisterCompleted")
public class UserRegisterCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterCompleted() {
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
		
		// セッションからユーザー登録画面で入力された値を取得
		String user_name = (String)session.getAttribute("user_name");
		String password = (String)session.getAttribute("password");
		UsersDao u_dao = null;
		try {
			u_dao = new UsersDao();
			//ユーザー登録するメソッドの呼び出し
			int user_id = u_dao.insert_user_info(user_name, password);
			//セッションへの書き込み
			session.setAttribute("user_id", user_id);
		} catch (Exception e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		// 完了画面に遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("user_register_completed.jsp");
		dispatch.forward(request, response);
	}

}
