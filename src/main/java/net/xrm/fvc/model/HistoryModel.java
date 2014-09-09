/**
 * 
 */
package net.xrm.fvc.model;

import java.io.Serializable;

/**
 * @author alexey.kvitko
 * @date Sep 7, 2014 1:38:10 PM
 * @comment create new HistoryModel class
 */
public class HistoryModel implements Serializable{

	private static final long serialVersionUID = -2829595101612160512L;
	
	private String fileName;
	private Integer revision;
	private String author;
	private String modDate;
	private String comment;
	private String classMethod;
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the revision
	 */
	public Integer getRevision() {
		return revision;
	}
	/**
	 * @param revision the revision to set
	 */
	public void setRevision(Integer revision) {
		this.revision = revision;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the modDate
	 */
	public String getModDate() {
		return modDate;
	}
	/**
	 * @param modDate the modDate to set
	 */
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the classMethod
	 */
	public String getClassMethod() {
		return classMethod;
	}
	/**
	 * @param classMethod the classMethod to set
	 */
	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
				.append("fileName:" +fileName+"\n")
				.append("revision:" +revision+"\n")
				.append("author: " + author+"\n")
				.append("mod_date: " + modDate+"\n")
				.append("comment: " + comment+"\n")
				.append("classMethod: " + classMethod+"\n");
		return sb.toString();
	}

	

}
