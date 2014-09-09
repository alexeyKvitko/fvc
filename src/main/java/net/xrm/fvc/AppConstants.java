/**
 * 
 */
package net.xrm.fvc;

import java.text.SimpleDateFormat;

/**
 * @author alexey.kvitko
 * @date Aug 22, 2014 12:43:12 PM
 * @comment create new class
 */
public class AppConstants {
	
	public static final String FIELD_FILE_NAME = "file_name";
	
	public static final String FILE_STATUS_NEW = "New";
	public static final String FILE_STATUS_MODIFIED = "Modified";
	
	public static final String ERROR_GET_ROOT = "app.error.root.not.exist";
	public static final String ERROR_CANNOT_GET_FILES = "app.error.cannot.get.files";
	public static final String ERROR_CANNOT_GET_HISTORY = "app.error.cannot.get.history";
	public static final String ERROR_CANNOT_READ_FILE = "app.error.cannot.read.file";
	public static final String ERROR_CANNOT_GET_REVISION = "app.error.cannot.get.revision";
	public static final String ERROR_CANNOT_UPDATE_FILE = "app.error.cannot.update.file";
	public static final String ERROR_HISTORY_EMPTY = "app.error.history.empty";
	
	public static final String WARN_NO_COMMIT_FILES = "app.error.no.files.commit";
	
	public static final String SUPPORTED_FILE_TYPES = "app.supported.file.types";
	
	public static final String MSG_NOT_FOUND = "app.msg.not.files.found";
	public static final String MSG_NOT_CHANGED = "app.msg.files.not.changed";
	public static final String MSG_SUCCESS_COMMITTED= "app.msg.success.committed.files";
	
	public static final String AN_AUTHOR = "app.annotation.author";
	public static final String AN_DATE = "app.annotation.date";
	public static final String AN_COMMENT = "app.annotation.comment";
	
	public static final String SYMBOL_PARENTHESIS="{";
	public static final String SYMBOL_SEMICOLON=";";
	public static final String SYMBOL_ASTERISK="*";
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
}
