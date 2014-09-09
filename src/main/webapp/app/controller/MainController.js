Ext.define('fvc.controller.MainController', {
    extend: 'Ext.app.Controller',
    views: ['MainView','TreeGridMenu','ConfirmCommit','HistoryView'],
    stores:['FileStore','MFIStore','HistoryStore'],
    models:['File'],
    refs: [
           {
        	   ref : 'dirPanel',
        	   selector: 'treepanel'
           },
           {
        	   ref : 'confirmCommitWindow',
        	   selector: 'window'
           }
           
           ],
    
    init: function() {
    	var me = this;
    	var view =  Ext.widget('mainview');
    	view.render(document.getElementById("mainDiv"));
    	me.control(
    				{
						'treegridmenu menuitem' : {
								click: function (item){
										var model = me.getDirPanel().getSelectionModel().getSelection();
										var isLeaf = model[0].isLeaf();
										if  ( item.itemId == 'idCommit' ){
											me.showConfirmDlg( model[0].getId() );	
										} else if ( item.itemId == 'idShowHistory' && isLeaf == true){
											me.showHistory( model[0].getId() );
										} else if ( item.itemId == 'idShowHistory' && isLeaf == false){
											Ext.cms.err(_msg_error, _msg_error_cannot_show_hisroty );
										} 
								}
						},
						'confirmcommit button[id=confirmBtnId]' :{
							click: function ( btn ){
								me.commitData();
							}
						}
    				});
    },
    commitData: function(){
    	var me = this;
    	var selModel = Ext.getCmp('confirmGrid').getSelectionModel().getSelection();
    	var checkedData = new Array();
    	for ( i = 0; i < selModel.length; i++ ){
    		checkedData[i] = selModel[i].data;
    	}
    	var mfiModels = Ext.encode(checkedData);
    	Ext.Ajax.request({
    		dataType: 'json',
    		url: CONTEXT_PATH+'commitSelected.do',
    		method: 'POST',
    		headers:{
    			'Accept': 'application/json',
    			'Content-Type' : 'application/json' 
    		},
    		jsonData:mfiModels,
    		writer :{
    			root: 'mfiModels'
    		},
    		success: function(action) {
    			var res = Ext.decode( action.responseText );
    			if ( res.success ){
    				Ext.cms.msg(_msg_success, res.message);
    			} else {
    				Ext.cms.msg(_msg_error, res.message);
    			}
    			Ext.data.StoreManager.lookup('FileStore').load();
    			var cw = Ext.getCmp('confirmWindowId');
    			cw.destroy();
    		}
    	});
    	
    }
    ,
    showConfirmDlg : function ( path ){
    	var store = Ext.data.StoreManager.lookup('MFIStore');
    	store.load({
    		params: {
    			path:path
    		},
    		callback: function ( records, operation, success){
    			if ( success ){
    				var confirmWindow = Ext.create("fvc.view.ConfirmCommit", {edit:true});
    		    	confirmWindow.path = path;
    		    	confirmWindow.show();
    			} else {
    				Ext.cms.err(_msg_error, store.getProxy().getReader().rawData.message);	
    			}
    		}
    	});    	
    	
    },
    
    showHistory : function ( path ){
    	var store = Ext.data.StoreManager.lookup('HistoryStore');
    	store.load({
    		params: {
    			path:path
    		},
    		callback: function ( records, operation, success){
    			if ( success ){
    				var historyWindow = Ext.create("fvc.view.HistoryView", {edit:true});
    		    	historyWindow.path = path;
    		    	historyWindow.show();
    			} else {
    				Ext.cms.err(_msg_error, store.getProxy().getReader().rawData.message);	
    			}
    		}
    	});    	
    }
   
       
});