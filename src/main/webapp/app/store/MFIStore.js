Ext.define('fvc.store.MFIStore', {
    extend: 'Ext.data.Store',
    requires: ['fvc.model.MFI'],
    model: 'fvc.model.MFI',
    storeId : 'mfiStore',
    autoLoad: false,
    autoSync: true,
    proxy : {
        type: 'ajax',
        url: CONTEXT_PATH+'getFilesToCommit.do',
		method: 'GET',
		reader: {
            type: 'json',
            readRecordsOnFailure: false,
            successProperty: 'success',
            root: 'mfiList',
            messageProperty: 'message'
        },   
    }
});