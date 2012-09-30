Ext.define('DepartmentsPanel', {
    extend: 'Ext.panel.Panel',

    initComponent: function() {
        Ext.apply(this, {
            region: 'west',
            title: 'Отделы',
            width: 270,
            split: true,
            collapsible: true,
            floatable: false,
            dockedItems: [{
                xtype: 'toolbar',
                items: [ this.createDepartmentAction ]
            }],
            items: this.createDepartmentsView(), 
        });

        this.callParent(arguments);
    },

    createDepartmentsView: function() {
        return Ext.create('widget.dataview', {
            store: Ext.create('Ext.data.Store', {
                model: 'Departments',
                data: [
                    { id: 0, title: 'Все сотрудники' },
                    { id: 1, title: 'Отдел A' },
                    { id: 2, title: 'Отдел B' }
                ] // TODO
            }),

            selModel: {
                mode: 'SINGLE',
                listeners: {
                    scope: this,
                    selectionchange: this.onSelectionChange
                }
            },

            // listeners: {
            //    scope: this,
            //    contextmenu: this.onContextMenu,
            //    viewready: this.onViewReady
            //},
            trackOver: true,
            cls: 'dep-list',
            itemSelector: 'div.dep-list-item',
            overItemCls: 'dep-list-item-hover',
            tpl: '<tpl for="."><div class="dep-list-item">{title}</div></tpl>'
        });
    },
    
    onSelectionChange: function ()
    {
        alert("Update grid");
        // TODO update grid
    }
});
