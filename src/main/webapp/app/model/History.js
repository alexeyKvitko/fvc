/**
 *  Model to describe modified file  
 * 
 */
Ext.define('fvc.model.History',{
	extend: 'Ext.data.Model',
	fields: [
	         { name: 'id'},
	         { name: 'revision', type: 'int' },
	         { name: 'author', type: 'string' },
	         { name: 'modDate', type: 'string' },
	         { name: 'comment', type: 'string' },
	         ]
	
});