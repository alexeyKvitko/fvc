 Ext.define('fvc.view.ConfirmCommit', {
    extend: 'Ext.window.Window',
    alias: 'widget.confirmcommit',
    id: 'confirmWindowId',
    path : null,
    constructor: function ( config ){
    	var me = this;
    	Ext.tip.QuickTipManager.init();
    	config = config || {};
    	var defaultsConfig  = {
    			title: config.title || _msg_title_commit,
    			store:  Ext.data.StoreManager.lookup('MFIStore'),
	    		closeAction: 'destroy',
			    modal: true,
			    layout: 'fit',
			    width : 600,
			    height: 300,
                buttonAlign: 'right',
                resizable: false,
                items: [{
                	xtype : 'grid',
                	id: 'confirmGrid',
                	selType: 'checkboxmodel',
                    columns:[
                             {
                            	 text: _msg_commit_grid_column_resource,
                            	 style: 'text-align:center',
                            	 dataIndex: 'fileName',
                            	 flex: 0.6
                             },
                             {
                            	 text: _msg_commit_grid_column_status,
                            	 style: 'text-align:center',
                            	 dataIndex: 'status',
                            	 flex: 0.2
                             }
                             ]
                }],
                buttons:[
                         {
                        	 text: _msg_btn_confirm,
                        	 id: 'confirmBtnId',
                        	 style: 'text-align:center',
                        	 scope: this
                         },
                         {
                        	 text: _msg_btn_cancel,
                        	 style: 'text-align:center',
                        	 handler: config.cancelCallBack || function() {
                             	this.destroy();
                             },
                             scope: this
                         }
                         ]
                
    	};
    	this.callParent([defaultsConfig]);
    },
    initComponent: function(){
    	var grid = this.items[0];
    	grid.store = Ext.data.StoreManager.lookup('MFIStore');
    	this.callParent( arguments );
    }
    
});