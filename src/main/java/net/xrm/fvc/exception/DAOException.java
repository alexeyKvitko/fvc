/**
 * 
 */
package net.xrm.fvc.exception;

/**
 * @author alexey.kvitko
 * @date Sep 1, 2014 3:13:58 PM
 * @comment class to generate DAO ecxeption
 */
public class DAOException extends Exception{

	private static final long serialVersionUID = 3242927487598368595L;
	
	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super( message );
	}

	public DAOException(String p_arg0, Throwable p_arg1) {
		super( p_arg0, p_arg1 );
	}

	public DAOException(Throwable p_arg0) {
		super( p_arg0 );
	}
 
}
