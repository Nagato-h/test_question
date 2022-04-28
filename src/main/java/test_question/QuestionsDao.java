package test_question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class QuestionsDao extends ConnectionDao {
	
	/**
	 * コンストラクタ
	 */
	public QuestionsDao() throws Exception {
		setConnection();
	}

	/**
	 * questions テーブルを全件取得
	 */
	public ArrayList<QuestionsBean> findAll() throws DAOException {
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
			String sql = "SELECT id, question FROM questions";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			ArrayList<QuestionsBean> list = new ArrayList<QuestionsBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionsBean bean = new QuestionsBean(id, question);
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
	public QuestionsBean find(int question_id) throws Exception {
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
			String sql = "SELECT id, question FROM questions WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, question_id);
			rs = st.executeQuery();
			QuestionsBean bean = new QuestionsBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				bean.setId(id);
				bean.setQuestion(question);
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
	 * 問題を登録するメソッド
	 */
	public int insert_question_info(String question) throws Exception {
		String sql = "insert into "
				+ "questions(id,question,created_at,updated_at) "
				+ "values (?,?,?,?)";
		ResultSet max_id = null;
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
			try(PreparedStatement stmt_1 = con.prepareStatement("SELECT MAX(id) as id FROM questions;")){
				max_id = stmt_1.executeQuery();
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
				stmt_2.setInt(1, column + 1);
				stmt_2.setString(2, question);
				stmt_2.setTimestamp(3, timestamp);
				stmt_2.setTimestamp(4, timestamp);
				stmt_2.executeUpdate();
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
		return column + 1;
	}
	
	/**
	 * 問題を更新するメソッド
	 */
	public void update_question_info(int question_id, String question) throws Exception {
		String sql = "update questions set question = ?, updated_at = ? where id = ?";
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (con == null) {
			try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		try {
			try(PreparedStatement stmt = con.prepareStatement(sql)){
				//変数sqlの一番目の?に引数のnameをセットする
				stmt.setString(1, question);
				stmt.setTimestamp(2, timestamp);
				stmt.setInt(3, question_id);
				stmt.executeUpdate();
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
	
	/**
	 * 問題を削除するメソッド
	 */
	public void delete_question_info(int question_id) throws Exception {
		String sql = "delete from questions where id = ?";
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