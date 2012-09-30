Ext.define('App', {
    extend: 'Ext.container.Viewport',
    
    initComponent: function() {
        Ext.apply(this, {
           mainPanel: this.createMainPanel(),
           employeeWindow: Ext.create('EmployeeWindow'),
           departmentWindow: Ext.create('DepartmentWindow')
        });

        this.callParent(arguments);
    },

    createMainPanel: function() {
        return Ext.create('Ext.panel.Panel', {
            height: 350,
            anchor: '100%',
            layout: 'border',
            bodyStyle: 'padding: 5px;',
            renderTo: Ext.getBody(),
            items: [ this.createDepartmentsPanel(), this.createEmployeesGrid() ]
        });
    },

    createEmployeesGrid: function() {
        return Ext.create('EmployeesGrid', {
            addEmployeeAction: this.createEmployeeAction() 
        });
    },
    
    createDepartmentsPanel: function() {
        return Ext.create('DepartmentsPanel', {
            createDepartmentAction: this.createDepartmentAction() 
        });
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
    
    createDepartmentAction: function() {
        var app = this;
        return Ext.create('Ext.Action', {
            iconCls: 'add-button',
            text: 'Добавить отдел',
            handler: function(widget, event) {
                app.departmentWindow.show();
            }
        });
    }
});

