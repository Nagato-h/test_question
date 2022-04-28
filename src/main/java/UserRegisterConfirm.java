

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserRegisterConfirm
 */
@WebServlet("/UserRegisterConfirm")
public class UserRegisterConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterConfirm() {
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
		
		//ユーザー登録画面で入力された値をパラメーターで取得
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		
		// セッションの開始
		HttpSession session = request.getSession(true);
		
		//セッションへの書き込み
		session.setAttribute("user_name", user_name);
		session.setAttribute("password", password);
		
		if (user_name.equals("") && password.equals("")) {
			request.setAttribute("error_1", "<span class=\"material-symbols-outlined\">error</span> ユーザー名を入力してください");
			request.setAttribute("error_2", "<span class=\"material-symbols-outlined\">error</span> パスワードを入力してください");
			RequestDispatcher dispatch = request.getRequestDispatcher("user_register.jsp");
			dispatch.forward(request, response);
			return;
		} else if (user_name.equals("")) {
			request.setAttribute("error_1", "<span class=\"material-symbols-outlined\">error</span> ユーザー名を入力してください");
			RequestDispatcher dispatch = request.getRequestDispatcher("user_register.jsp");
			dispatch.forward(request, response);
			return;
		} else if (password.equals("")) {
			request.setAttribute("error_2", "<span class=\"material-symbols-outlined\">error</span> パスワードを入力してください");
			RequestDispatcher dispatch = request.getRequestDispatcher("user_register.jsp");
			dispatch.forward(request, response);
			return;
		}
		
		//問題・回答が空でなく文字数制限にかからなかったら確認画面に遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("user_register_confirm.jsp");
		dispatch.forward(request, response);
	}

}
