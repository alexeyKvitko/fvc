/**
 *  Model to describe modified file  
 * 
 */
Ext.define('fvc.model.MFI',{
	extend: 'Ext.data.Model',
	fields: [
	         { name: 'id'},
	         { name: 'fileName', type: 'string' },
	         { name: 'status', type: 'string' },
	         ]
	
});