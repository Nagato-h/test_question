package test_question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UsersDao extends ConnectionDao {
	
	/**
	 * コンストラクタ
	 */
	public UsersDao() throws Exception {
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	public List<UsersBean> findAll() throws DAOException {
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
			String sql = "SELECT id, name, password FROM users WHERE deleteflag = 0";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<UsersBean> list = new ArrayList<UsersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				UsersBean bean = new UsersBean(id, name, pass);
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
	public UsersBean find(int user_id) throws Exception {
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
			String sql = "SELECT id, name, password FROM users WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, user_id);
			rs = st.executeQuery();
			UsersBean bean = new UsersBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(pass);
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
	 * ユーザー登録するメソッド
	 */
	public int insert_user_info(String user_name, String password) throws Exception {
		String sql = "insert into "
				+ "users(id,name,password,created_at,updated_at) "
				+ "values (?,?,?,?,?)";
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
			try(PreparedStatement stmt_1 = con.prepareStatement("SELECT MAX(id) as id FROM users;")){
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
				stmt_2.setString(2, user_name);
				stmt_2.setString(3, password);
				stmt_2.setTimestamp(4, timestamp);
				stmt_2.setTimestamp(5, timestamp);
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
	 * ユーザー名を更新するメソッド
	 */
	public void update_user_name(int user_id, String new_user_name) throws Exception {
		String sql = "update users set name = ?, updated_at = ? where id = ?";
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
				stmt.setString(1, new_user_name);
				stmt.setTimestamp(2, timestamp);
				stmt.setInt(3, user_id);
				stmt.executeUpdate();
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
	 * パスワードを更新するメソッド
	 */
	public void update_password(int user_id, String new_password) throws Exception {
		String sql = "update users set password = ?, updated_at = ? where id = ?";
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
				stmt.setString(1, new_password);
				stmt.setTimestamp(2, timestamp);
				stmt.setInt(3, user_id);
				stmt.executeUpdate();
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
	 * ユーザーを削除するメソッド(delete_flagを1に変更)
	 */
	public void delete_user(int user_id) throws Exception {
		String sql = "update users set delete_flag = 1, deleted_at = ? where id = ?";
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
				stmt.setTimestamp(1, timestamp);
				stmt.setInt(2, user_id);
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
}