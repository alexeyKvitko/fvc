/**
 * 
 */
package net.xrm.fvc.dao.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The POJO class to mapping the structure of table in  database 
 * 
 * @author alexey.kvitko
 * @date Sep 1, 2014 2:56:30 PM
 * @comment Create new pojo class
 */
@Entity
@Table(name="modified_file")
public class ModifiedFileInfo implements Serializable{

	private static final long serialVersionUID = -3454566269492075226L;
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "crc32")	
	private Long crc32;
	
	@Column(name = "revision")
	private Integer revision;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "commit_date")
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
	 * @return the crc32
	 */
	public Long getCrc32() {
		return crc32;
	}

	/**
	 * @param crc32 the crc32 to set
	 */
	public void setCrc32(Long crc32) {
		this.crc32 = crc32;
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
	public String toString() {
		StringBuilder sb = new StringBuilder()
				.append("id: "+ id.toString()+"\n")
				.append("fileName:" +fileName+"\n")
				.append("crs32: " + crc32.toString()+"\n")
				.append("revision: " + revision.toString()+"\n")
				.append("author: " + author.toString()+"\n")
				.append("commitDate: " + commitDate.toString()+"\n");
		return sb.toString();
	}
	
	

}
	