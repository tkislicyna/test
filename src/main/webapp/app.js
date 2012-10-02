Ext.define('App', {
    extend: 'Ext.container.Viewport',
    layout: 'anchor',

    initComponent: function() {
        Ext.apply(this, {
           items: this.createMainPanel(),
           employeeWindow: Ext.create('EmployeeWindow', {departmentList: [
                        { id: 1, title: 'Отдел A' },
                        { id: 2, title: 'Отдел B' }
                    ]}),
           departmentWindow: Ext.create('DepartmentWindow')
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
        this.employeesGrid = Ext.create('EmployeesGrid', {
            addEmployeeAction: this.createEmployeeAction(),
            editEmployeeAction: this.editEmployeeAction()
        });
        return this.employeesGrid;
    },

    createDepartmentsPanel: function() {
        this.departmentsPanel = Ext.create('DepartmentsPanel', {
            createDepartmentAction: this.createDepartmentAction(),
            listeners: { scope: this, departmentSelect: this.onDepartmentSelect }
        });
        return this.departmentsPanel;
    },

    createEmployeeAction: function() {
        var app = this;
        return Ext.create('Ext.Action', {
            iconCls: 'add-user',
            text: 'Добавить сотрудника',
            handler: function(widget, event) {
                app.employeeWindow.show();
            }
        });
    },

    editEmployeeAction: function() {
        var app = this;
        return Ext.create('Ext.Action', {
            iconCls: 'add-user',
            text: 'Редактировать сотрудника',
            handler: function(widget, event) {
                console.log(app);
                app.employeeWindow.show();
            }
        });
    },

    createDepartmentAction: function() {
        var app = this;
        return Ext.create('Ext.Action', {
            iconCls: 'add-button',
            text: 'Добавить отдел',
            handler: function(widget, event) {
                app.departmentWindow.show();
            }
        });
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

