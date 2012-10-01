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
        this.addEvents('departmentSelect');
        this.callParent(arguments);
    },

    createDepartmentsView: function() {
        return Ext.create('widget.dataview', {
            store: Ext.create('Ext.data.Store', {
                model: 'Departments',
                proxy: {
                    type: 'ajax',
                    url: 'rs/departments',
                    reader: {
                        type: 'json',
                        idProperty: 'id.id'
                    },
		            pageParam: undefined,
		            limitParam: undefined,
		            startParam: undefined,
		            noCache: false
                },
                autoLoad: true
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
            tpl: '<tpl for="."><div class="dep-list-item">{title} (тел. {phoneNumber})</div></tpl>'
        });
    },
    
    onSelectionChange: function (dep, selections) {
        this.fireEvent('departmentSelect', this, selections[0].data);
    }
});
