Ext.define('DepartmentsPanel', {
    extend: 'Ext.panel.Panel',
    region: 'west',
    title: 'Отделы',
    width: 270,
    split: true,
    collapsible: true,
    floatable: false,

    initComponent: function() {
        Ext.apply(this, {
            dockedItems: [{
                xtype: 'toolbar',
                items: [ this.createDepartmentAction ]
            }],
            items: [ this.createAllEmployeesView(), this.createDepartmentsView()]
        });
        this.addEvents('departmentSelect');
        this.callParent(arguments);
    },

    createDepartmentsView: function() {
        this.departmentsView = Ext.create('widget.dataview', {
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
                    selectionchange: this.onDeparmentSelectionChange
                }
            },
            trackOver: true,
            cls: 'dep-list',
            itemSelector: 'div.dep-list-item',
            overItemCls: 'dep-list-item-hover',
            tpl: '<tpl for="."><div class="dep-list-item">{title} (тел. {phoneNumber})</div></tpl>'
        });

        return this.departmentsView;
    },

    createAllEmployeesView: function() {
        this.allEmployeesView = Ext.create('widget.dataview', {
            store: Ext.create('Ext.data.Store', {
                model: 'Departments',
                data: [{
                    title : 'Все сотрудники'
                }]
            }),
            selModel: {
                mode: 'SINGLE',
                listeners: {
                    scope: this,
                    selectionchange: this.onAllEmployeesSelectionChange
                }
            },
            listeners: {
                scope: this,
                viewready: this.onViewReady
            },
            trackOver: true,
            cls: 'dep-list',
            itemSelector: 'div.dep-list-item',
            overItemCls: 'dep-list-item-hover',
            tpl: '<tpl for="."><div class="dep-list-item">{title}</div></tpl>'
        });

        return this.allEmployeesView;
    },

    onDeparmentSelectionChange: function (view, selections) {
        if (selections.length > 0) {
            this.allEmployeesView.getSelectionModel().deselectAll(true);
            this.fireEvent('departmentSelect', this, selections[0].data);
        }
    },

    onAllEmployeesSelectionChange: function (view, selections) {
        if (selections.length > 0) {
            this.departmentsView.getSelectionModel().deselectAll(true);
            this.fireEvent('departmentSelect', this, null);
        }
    },

    onViewReady: function () {
        this.allEmployeesView.getSelectionModel().select(0);
    }
});
