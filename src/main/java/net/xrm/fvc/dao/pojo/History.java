/**
 * 
 */
package net.xrm.fvc.dao.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author alexey.kvitko
 * @date Sep 7, 2014 11:52:50 AM
 * @comment create new class History
 */
@Entity
@Table(name="history")
public class History implements Serializable{
	
	
	private static final long serialVersionUID = -8616520843477199258L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "revision")
	private Integer revision;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "mod_date")
	private String modDate;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "cls_mtd")
	private String classMethod;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
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
				.append("id: "+ id.toString()+"\n")
				.append("fileName:" +fileName+"\n")
				.append("revision:" +revision+"\n")
				.append("author: " + author+"\n")
				.append("mod_date: " + modDate+"\n")
				.append("comment: " + comment+"\n")
				.append("classMethod: " + classMethod+"\n");
		return sb.toString();
	}
	
}
