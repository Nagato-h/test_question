

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test_question.UsersBean;
import test_question.UsersDao;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		// 未入力時の処理
		String  password = request.getParameter("password");
		if (request.getParameter("id").equals("") && password.equals("")) {
			request.setAttribute("error_1", "<span class=\"material-symbols-outlined\">error</span> ユーザーIDを入力してください");
			request.setAttribute("error_2", "<span class=\"material-symbols-outlined\">error</span> パスワードを入力してください");
			RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
			dispatch.forward(request, response);
			return;
		} else if (request.getParameter("id").equals("")) {
			request.setAttribute("error_1", "<span class=\"material-symbols-outlined\">error</span> ユーザーIDを入力してください");
			RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
			dispatch.forward(request, response);
			return;
		} else if (password.equals("")) {
			request.setAttribute("error_2", "<span class=\"material-symbols-outlined\">error</span> パスワードを入力してください");
			RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
			dispatch.forward(request, response);
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			UsersDao dao = new UsersDao();
			UsersBean list = dao.find(id);
			
			//ID・パスワードが一致した場合
			if (id == list.getId() && list.getPassword().equals(password)) {
				HttpSession session = request.getSession(true);
				//セッションへの書き込み
				session.setAttribute("login", "OK");
				session.setAttribute("user_id", list.getId());
				session.setAttribute("user_name", list.getName());
				session.setAttribute("password", list.getPassword());
				RequestDispatcher dispatch = request.getRequestDispatcher("top.jsp");
				dispatch.forward(request, response);
				return;
			}
			
			//listにデータが取得できなかった
			if (list.getId() == 0) {
				request.setAttribute("error_1", "<span class=\"material-symbols-outlined\">error</span> ユーザーIDが存在しません");
				RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
				dispatch.forward(request, response);
				return;
			}

			//パスワードが一致しなかった
			if (!(list.getPassword().equals(password))) {
				request.setAttribute("error_2", "<span class=\"material-symbols-outlined\">error</span> パスワードが一致しません");
				RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
				dispatch.forward(request, response);
				return;
			}
			
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
