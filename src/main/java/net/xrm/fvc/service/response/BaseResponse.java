/**
 * 
 */
package net.xrm.fvc.service.response;

import java.io.Serializable;

/**
 * This class used to response common JSON data
 * 
 * @author alexey.kvitko
 * @date Sep 1, 2014 5:00:20 PM
 * @comment
 */
public class BaseResponse implements Serializable{

	private static final long serialVersionUID = 672422148228525291L;
	private Boolean success;
	private String  message;
	
	
	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("success: "+success+"\n")
		.append("message:"+message+"\n");
		return sb.toString();
	}

}
