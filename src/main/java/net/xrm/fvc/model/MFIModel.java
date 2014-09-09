/**
 * 
 */
package net.xrm.fvc.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author alexey.kvitko
 * @date Sep 2, 2014 10:40:51 AM
 * @comment New Class
 * 
 *  This class described structure which will be converted to JSON, which returned to front-end for modified files
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFIModel implements Serializable{
	
	private Integer id;
	private String fileName;
	private String  status;
	private Integer revision;
	private String author;
	private Timestamp commitDate;
	
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the commitDate
	 */
	public Timestamp getCommitDate() {
		return commitDate;
	}
	/**
	 * @param commitDate the commitDate to set
	 */
	public void setCommitDate(Timestamp commitDate) {
		this.commitDate = commitDate;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Class Name: ["+this.getClass().getName()+"]\n" )
		  .append("id: ["+id+"]\n" )
		  .append("fileName: ["+fileName+"]\n" )
		  .append("status: ["+status+"]\n" )
		  .append("revision: " + revision.toString()+"\n")
		  .append("author: " + author.toString()+"\n")
		  .append("commitDate: " + commitDate.toString()+"\n");
		  
		return sb.toString();
	}
	
}
