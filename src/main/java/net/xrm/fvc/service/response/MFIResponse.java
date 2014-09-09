/**
 * 
 */
package net.xrm.fvc.service.response;

import java.io.Serializable;
import java.util.List;

import net.xrm.fvc.model.MFIModel;

/**
 * 
 * This class used to response modified supported files
 * 
 * @author alexey.kvitko
 * @date Sep 1, 2014 5:04:06 PM
 * @comment
 */
public class MFIResponse extends BaseResponse implements Serializable{

	private static final long serialVersionUID = 5906984680619286267L;
	
	private List< MFIModel > mfiList;
	
	/**
	 * @return the mfiList
	 */
	public List< MFIModel > getMfiList() {
		return mfiList;
	}

	/**
	 * @param mfiList the mfiList to set
	 */
	public void setMfiList(List< MFIModel > mfiList) {
		this.mfiList = mfiList;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("files size: "+mfiList.size()+"\n");
		return sb.toString();
	}

}
