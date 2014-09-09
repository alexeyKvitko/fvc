/**
 * 
 */
package net.xrm.fvc.exception;

/**
 * @author alexey.kvitko
 * @date Sep 1, 2014 3:50:49 PM
 * @comment
 */
public class BusinessLogicException extends Exception {
	
	private static final long serialVersionUID = -7445350398975680888L;

	/** Default constructor */
	public BusinessLogicException(){
		super();
	}

	/** Constructs new BusinessLogicException from specified exception*/ 
	public BusinessLogicException( Exception e ){
		super( e.getMessage() );
	}

	/** Constructs new BusinessLogicException with specified detail message*/
	public BusinessLogicException( String message )
	{
		super( message );
	}

	/**Constructs a new BusinessLogicException with the specified detail message and cause*/
	public BusinessLogicException( String message, Throwable cause )
	{
		super( message, cause );
	}

	/**Constructs a new BusinessLogicException with the specified cause*/
	public BusinessLogicException( Throwable cause )
	{
		super( cause );
	}
}
