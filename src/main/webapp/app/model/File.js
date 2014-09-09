/**
 *  Model to files from dir  
 * 
 */
Ext.define('fvc.model.File',{
	extend: 'Ext.data.Model',
	fields: [
	         { name: 'text', type: 'string' },
	         { name: 'leaf', type: 'boolean' },
	         { name: 'expended', type: 'boolean' },
	         { name: 'revision', type: 'int', convert: null },
	         { name: 'author', type: 'string' },
	         { name: 'modifiedDate', type: 'string' }
	         
	         ]
	
});