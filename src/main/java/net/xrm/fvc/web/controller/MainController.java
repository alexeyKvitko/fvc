/**
 * 
 */
package net.xrm.fvc.web.controller;

import net.xrm.fvc.model.MFIModel;
import net.xrm.fvc.service.MainService;
import net.xrm.fvc.service.impl.MainServiceImpl;
import net.xrm.fvc.service.response.BaseResponse;
import net.xrm.fvc.service.response.FileResponse;
import net.xrm.fvc.service.response.HistoryResponse;
import net.xrm.fvc.service.response.MFIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author alexey.kvitko
 * @date Aug 22, 2014 1:19:32 PM
 * @comment Create controller
 */
@Controller
public class MainController {
	
	
	@Autowired
	MainService mainService;
	
	/**
	 * Redirect to main page
	 * @return String - main application page
	 */
	@RequestMapping("/showMainPage.do")
	protected String showMapForm() {
		return "mainPage";
	}
	
	/**
	 * Return files for selected node
	 * @param path - directory node
	 * @return FileResponse
	 */
	@RequestMapping(value="/getFiles.do", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody FileResponse getFiles(@RequestParam("node") String path){
		FileResponse response = mainService.getFiles( path );
		return response;
	}
	
	/**
	 * Return supported files for commit
	 * @param path - directory or file to commit
	 * @return MFIResponse
	 */
	@RequestMapping(value="/getFilesToCommit.do", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody MFIResponse getFilesToCommit(@RequestParam("path") String path){
		MFIResponse response = mainService.getSupportedModifiedFiles( path );
		return response;
	}
	
	/**
	 * Commit files selected by user
	 * @param mfiModels - Array of files info
	 * @return BaseResponse
	 */
	@RequestMapping(value="/commitSelected.do", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody BaseResponse commitSelectedFiles(@RequestBody final MFIModel[] mfiModels){
		( (MainServiceImpl) mainService ).setCurrentRevision();
		BaseResponse response =  mainService.parseFileAndSaveHistoryToDB( mfiModels );
		return response;
	}
	
	/**
	 * Return history for selected file
	 * @param path - File
	 * @return HistoryResponse
	 */
	@RequestMapping(value="/showHistory.do", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HistoryResponse showHistory(@RequestParam("path") String path){
		HistoryResponse response = mainService.getHistoryForFile( path );
		return response;
	}
	
	
}
