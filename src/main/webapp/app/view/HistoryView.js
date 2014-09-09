 Ext.define('fvc.view.HistoryView', {
    extend: 'Ext.window.Window',
    alias: 'widget.historyview',
    path : null,
    constructor: function ( config ){
    	var me = this;
    	Ext.tip.QuickTipManager.init();
    	config = config || {};
    	var defaultsConfig  = {
    			title: config.title || _msg_title_history,
    			store:  Ext.data.StoreManager.lookup('HistoryStore'),
	    		closeAction: 'destroy',
			    modal: true,
			    layout: 'fit',
			    width : 800,
			    height: 400,
                buttonAlign: 'right',
                resizable: false,
                items: [{
                	xtype : 'grid',
                	id: 'historyGrid',
                    columns:[
                             {
                            	 text: _msg_history_grid_column_revision,
                            	 style: 'text-align:center',
                            	 dataIndex: 'revision',
                            	 flex: 0.1
                             },
                             {
                            	 text: _msg_history_grid_column_author,
                            	 style: 'text-align:center',
                            	 dataIndex: 'author',
                            	 flex: 0.2
                             },
                             {
                            	 text: _msg_history_grid_column_date,
                            	 style: 'text-align:center',
                            	 dataIndex: 'modDate',
                            	 flex: 0.2
                             },
                             {
                            	 text: _msg_history_grid_column_comment,
                            	 style: 'text-align:center',
                            	 dataIndex: 'comment',
                            	 flex: 0.5
                             }
                             ]
                }],
                buttons:[
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
    	grid.store = Ext.data.StoreManager.lookup('HistoryStore');
    	this.callParent( arguments );
    }
    
});