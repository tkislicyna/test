Ext.define('App', {
    extend: 'Ext.container.Viewport',
    layout: 'anchor',

    initComponent: function() {
        Ext.apply(this, {
           items: this.createMainPanel()
        });

        this.callParent(arguments);
    },

    createMainPanel: function() {
        return Ext.create('Ext.panel.Panel', {
            anchor: '100% 100%',
            layout: 'border',
            bodyStyle: 'padding: 5px;',
            renderTo: Ext.getBody(),
            items: [ this.createDepartmentsPanel(), this.createEmployeesGrid() ]
        });
    },

    createEmployeesGrid: function() {
        this.employeesGrid = Ext.create('EmployeesGrid');
        return this.employeesGrid;
    },

    createDepartmentsPanel: function() {
        this.departmentsPanel = Ext.create('DepartmentsPanel', {
            listeners: { scope: this, departmentSelect: this.onDepartmentSelect }
        });
        return this.departmentsPanel;
    },

    onDepartmentSelect: function(panel, dep) {
        if (dep) {
            this.employeesGrid.store.getProxy().extraParams.departmentId=dep.id.id;
        } else {
            delete this.employeesGrid.store.getProxy().extraParams.departmentId;
        }
        this.employeesGrid.store.load();
    }
});

