/**
 * 
 */
package net.xrm.fvc.service.response;

import java.io.Serializable;
import java.util.List;

import net.xrm.fvc.model.FileNode;

/**
 * This class used to response all files from drive
 * 
 * @author alexey.kvitko
 * @date Aug 24, 2014 1:36:24 PM
 * @comment
 */

public class FileResponse extends BaseResponse implements Serializable{

	private static final long serialVersionUID = 1973225696065833154L;

	private List< FileNode > fileNode;
	
			
	/**
	 * @return the fileNode
	 */
	public List<FileNode> getFileNode() {
		return fileNode;
	}
	/**
	 * @param fileNode the fileNode to set
	 */
	public void setFileNode(List<FileNode> fileNode) {
		this.fileNode = fileNode;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("files size: "+fileNode.size()+"\n");
		return sb.toString();
	}

}
