/**
 * 
 */
package net.xrm.fvc.model;

import java.io.Serializable;

/**
 * @author alexey.kvitko
 * @date Aug 24, 2014 10:54:47 AM
 * @comment create file node class
 * 
 * This class described structure which will be converted to JSON, which returned to front-end 
 */
@SuppressWarnings("serial")
public class FileNode implements Serializable{

	private String id;
	private String text;
	private Integer revision;
	private String author;
	private String modifiedDate;
	private boolean leaf;
	private boolean expanded;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return the expanded
	 */
	public boolean isExpanded() {
		return expanded;
	}

	/**
	 * @param expanded the expanded to set
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Class Name: ["+this.getClass().getName()+"]\n" )
		  .append("id: ["+id+"]\n" )
		  .append("text: ["+text+"]\n" )
		  .append("revision: ["+revision+"]\n" )
		  .append("author: ["+revision+"]\n" )
		  .append("modifiedDate: ["+modifiedDate+"]\n" )
		  .append("is leaf: ["+leaf+"]\n" )
		  .append("expended: ["+expanded+"]\n" );
		  
		return sb.toString();
	}
	
}
