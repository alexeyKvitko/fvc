Ext.define('fvc.store.HistoryStore', {
    extend: 'Ext.data.Store',
    requires: ['fvc.model.History'],
    model: 'fvc.model.History',
    storeId : 'historyStore',
    autoLoad: false,
    autoSync: true,
    proxy : {
        type: 'ajax',
        url: CONTEXT_PATH+'showHistory.do',
		method: 'GET',
		reader: {
            type: 'json',
            readRecordsOnFailure: false,
            successProperty: 'success',
            root: 'histories',
            messageProperty: 'message'
        },   
    }
});