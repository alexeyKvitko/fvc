
Ext.define('fvc.store.FileStore', {
    extend: 'Ext.data.TreeStore',
    requires: ['fvc.model.File'],
    model: 'fvc.model.File',
    storeId: 'fileStore',
    defaultRooId: '',
    autoLoad: false,
    proxy : {
        type: 'ajax',
        url: CONTEXT_PATH+'getFiles.do',
        reader: {
            type: 'json',
            readRecordsOnFailure: false,
            successProperty: 'success',
            root: 'fileNode',
            messageProperty: 'message'
        }   
    },
    listeners: {
    	'load': function ( store, records, success ){
    		if ( ! success ){
    			Ext.cms.err(_msg_error, store.getProxy().getReader().rawData.message);
    		}
    	}
    }
});