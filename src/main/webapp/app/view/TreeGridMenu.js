Ext.define('fvc.view.TreeGridMenu', {
    extend: 'Ext.menu.Menu',
    alias : 'widget.treegridmenu',
    id: 'treegridId',
    myY: -1,
    items : [
         {
        	 xtype: 'menuitem',
        	 text :_msg_menu_commit,
        	 itemId : 'idCommit',
        	 iconCls: 'edit'
    	},
    	{
    	 xtype: 'menuitem',
       	 text :_msg_menu_show_history,
       	 itemId : 'idShowHistory',
       	 iconCls: 'edit'
   	}
    	],
    	listeners: {
            show: function () {
            	this.setY(this.myY);
            }
        },
     setMyY : function( myY ){
        	this.myY = myY;
        }  
    
 });