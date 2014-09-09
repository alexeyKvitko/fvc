Ext.define('fvc.view.MainView', {
    extend: 'Ext.panel.Panel',
    alias : 'widget.mainview',
    requires: [
               'Ext.data.*',
               'Ext.grid.*',
               'Ext.tree.*'
               ],
    title: _msg_title_fvc,
    height: '100%',
    width: '100%',
    autoScroll: true,
    contextMenu: null,
    initComponent: function(){
    	var me = this;
    	me.contextMenu = this.buildMenu();
    	Ext.applyIf(me,{
    		items:[
    		       {
    		    	   xtype: 'treepanel',
    		    	   width: 876,
    		    	   useArrows: true,
    		    	   rootVisible: false,
    		    	   multiSelect: false,
    		    	   singleExpand: false,
    		    	   store: 'FileStore',
    		    	   viewConfig: {
    		    		    stripeRows: true,
    	                    listeners: {
    	                        itemcontextmenu: {
    	                            fn: me.onItemContextMenu,
    	                            scope: me
    	                        }
    	                    }
    	                },
    		    	   columns:[
    						{
    							xtype: 'treecolumn',
    							text: _msg_tree_column_path,
    							dataIndex: 'text',
    							flex: 0.5,
    							sortable:false
    						},
        			    	{
        			    		text: _msg_tree_column_revision,
        			    		dataIndex: 'revision',
        			    		sortable:false,
        			    		flex: 0.15
        			    	},
        			    	{
        			    		text: _msg_tree_column_author,
        			    		dataIndex: 'author',
        			    		sortable:false,
        			    		flex: 0.20
        			    	},
        			    	{
        			    		text: _msg_tree_column_modified,
        			    		dataIndex: 'modifiedDate',
        			    		sortable:false,
        			    		flex: 0.15
        			    	}
        			    	
        		    	]
        		}
    		
    		]
    	});
    		
    	me.callParent( arguments );
    },
   
    buildMenu : function(){
        var menu =  Ext.create('fvc.view.TreeGridMenu',{});
        return menu;
    },
 
    onDestroy : function(){
        this.menu.destroy();
        this.callParent(arguments);
    },
 
    onItemContextMenu : function(view,rec,item,index,event){
        event.stopEvent();
        this.contextMenu.setMyY( event.getY() );
        this.contextMenu.showAt(event.getXY());
        return false;
    }
	  	
});