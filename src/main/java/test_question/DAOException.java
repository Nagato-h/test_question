package test_question;

/** Exceptionを基にDAOExceptionを拡張する**/
public class DAOException extends Exception {

	/** 引数付きのコンストラクタ呼び出し **/
	public DAOException(String message) {

		super(message);

	}

}
