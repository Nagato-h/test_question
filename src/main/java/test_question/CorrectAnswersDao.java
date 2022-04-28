package test_question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class CorrectAnswersDao extends ConnectionDao {
	
	/**
	 * コンストラクタ
	 */
	public CorrectAnswersDao() throws Exception {
		setConnection();
	}

	/**
	 * questions テーブルを全件取得
	 */
	public ArrayList<CorrectAnswersBean> findAll() throws DAOException {
		if (con == null) {
			try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question_id, answer FROM correct_answers";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int question_id = rs.getInt("question_id");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, question_id, answer);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}
					
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("リソースの開放に失敗しました");

			}
		}
	}

	/**
	 * 指定IDのレコードを取得する
	 * @throws Exception 
	 */
	public CorrectAnswersBean find(int answer_id) throws Exception {
		if (con == null) {
			try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question_id, answer FROM correct_answers WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, answer_id);
			rs = st.executeQuery();
			CorrectAnswersBean bean = new CorrectAnswersBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				int question_id = rs.getInt("question_id");
				String answer = rs.getString("answer");
				bean.setId(id);
				bean.setQuestionId(question_id);
				bean.setAnswer(answer);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 指定question_idのレコードを取得する
	 * @throws Exception 
	 */
	public ArrayList<CorrectAnswersBean> edit_find(int answer_id) throws Exception {
		if (con == null) {
			try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question_id, answer FROM correct_answers WHERE question_id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, answer_id);
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int question_id = rs.getInt("question_id");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, question_id, answer);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
						rs.close();
				}				
				if (st != null) {
						st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 答えを登録するメソッド
	 */
	public void insert_answer_info(int question_id, String[] answers) throws Exception {
		String sql = "insert into "
				+ "correct_answers(id,question_id,answer,created_at,updated_at) "
				+ "values (?,?,?,?,?)";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		int column = 0;
		if (con == null) {
			try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		try {
			try(PreparedStatement stmt_1 = con.prepareStatement("SELECT MAX(id) as id FROM correct_answers;")){
				ResultSet max_id = stmt_1.executeQuery();
				// next()メソッド （次の行に移動する）で最初の行に移動する
				// while文で実行結果の情報を先頭から最後尾まで順に追う
				while (max_id.next()) {
				    column = max_id.getInt("id");
				}
			} catch(Exception e) {
				con.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
			try(PreparedStatement stmt_2 = con.prepareStatement(sql)){
				//変数sqlの一番目の?に引数のnameをセットする
				for(int i = 0; i < answers.length; i++) {
					stmt_2.setInt(1, column + 1);
					stmt_2.setInt(2, question_id);
					stmt_2.setString(3, answers[i]);
					stmt_2.setTimestamp(4, timestamp);
					stmt_2.setTimestamp(5, timestamp);
					stmt_2.executeUpdate();
					column = column + 1;
				}
				con.commit();
			} catch(Exception e) {
				con.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	/**
	 * 答えを更新するメソッド
	 */
	public void update_answer_info(int question_id, String[] answers) throws Exception {
		String d_sql = "delete from correct_answers where question_id = ?";
		String max_sql = "SELECT MAX(id) as id FROM correct_answers;";
		String i_sql = "insert into "
				+ "correct_answers(id,question_id,answer,created_at,updated_at) "
				+ "values (?,?,?,?,?)";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		int column = 0;
		if (con == null) {
			try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		try {
			// 編集した問題の答えをリセット
			try(PreparedStatement stmt_0 = con.prepareStatement(d_sql)){
				//変数sqlの一番目の?に引数のnameをセットする
				for(int i = 0; i < answers.length; i++) {
					stmt_0.setInt(1, question_id);
					stmt_0.executeUpdate();
				}
				//con.commit();
			} catch(Exception e) {
				con.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
			// IDの最大値を取得
			try(PreparedStatement stmt_1 = con.prepareStatement(max_sql)){
				ResultSet max_id = stmt_1.executeQuery();
				// next()メソッド （次の行に移動する）で最初の行に移動する
				// while文で実行結果の情報を先頭から最後尾まで順に追う
				while (max_id.next()) {
				    column = max_id.getInt("id");
				}
			} catch(Exception e) {
				con.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
			// 答えを登録
			try(PreparedStatement stmt_2 = con.prepareStatement(i_sql)){
				//変数sqlの一番目の?に引数のnameをセットする
				for(int i = 0; i < answers.length; i++) {
					stmt_2.setInt(1, column + 1);
					stmt_2.setInt(2, question_id);
					stmt_2.setString(3, answers[i]);
					stmt_2.setTimestamp(4, timestamp);
					stmt_2.setTimestamp(5, timestamp);
					stmt_2.executeUpdate();
					column = column + 1;
				}
				//con.commit();
			} catch(Exception e) {
				//con.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	/**
	 * 答えを削除するメソッド
	 */
	public void delete_answer_info(int question_id) throws Exception {
		String sql = "delete from correct_answers where question_id = ?";
		if (con == null) {
			try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		try {
			try(PreparedStatement stmt_0 = con.prepareStatement(sql)){
				stmt_0.setInt(1, question_id);
				stmt_0.executeUpdate();
				//con.commit();
			} catch(Exception e) {
				con.rollback();
				System.out.println("データ処理が正常に終了しなかったため、RollBackを行いました");
				throw e;
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}