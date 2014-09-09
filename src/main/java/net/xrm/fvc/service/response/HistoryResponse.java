package net.xrm.fvc.service.response;

import java.io.Serializable;
import java.util.List;

import net.xrm.fvc.model.HistoryModel;

/**
 * 
 * This class used to response history of file
 * 
 * @author alexey.kvitko
 * @date Sep 1, 2014 6:07:06 PM
 * @comment
 */
public class HistoryResponse extends BaseResponse implements Serializable{

	
	private static final long serialVersionUID = -6383486886322606423L;
	private List< HistoryModel > histories;

	/**
	 * @return the histories
	 */
	public List<HistoryModel> getHistories() {
		return histories;
	}

	/**
	 * @param histories the histories to set
	 */
	public void setHistories(List<HistoryModel> histories) {
		this.histories = histories;
	}



	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("histories size: "+histories.size()+"\n");
		return sb.toString();
	}

}
