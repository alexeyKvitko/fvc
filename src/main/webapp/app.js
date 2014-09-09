/**
 *  Main application
 */
var activeWidget ='undefined';
Ext.Loader.setConfig({  enabled: true });
Ext.tip.QuickTipManager.init();
Ext.require([
    'Ext.panel.*',
    'Ext.toolbar.*',
    'Ext.button.*',
    'Ext.container.ButtonGroup',
    'Ext.layout.container.Table'

]);

Ext.application({
    name: 'fvc',
    appFolder: 'app',
    controllers: [
        'MainController'
    ],
     launch: function() {
        Ext.create('Ext.container.Viewport', {
         
        });
    }
    
   
});