Ext.define('EmployeeWindow', {
    extend: 'Ext.window.Window',
    title: 'Добавить сотрудника',
    closable: true,
    closeAction: 'hide',
    width: 270,
    minWidth: 230,
    height: 170,
    layout: 'fit',
    plain: true,
    data: [],
    buttons: [{
        text: 'Сохранить'
    },{
        text: 'Отмена'
    }],

    initComponent: function() {
        Ext.apply(this, {
            items: this.createForm()
        });

        this.callParent(arguments);
    },

    createForm: function() {
        return Ext.create('Ext.form.Panel', {
            url: 'save-form.php',
            defaultType: 'textfield',
            border: false,
            bodyPadding: 7,
            fieldDefaults: { labelWidth: 60 },
            items: [{
                fieldLabel: 'Фамилия',
                allowBlank: false,
                name: 'lastName',
                anchor: '100%'
            }, {
                fieldLabel: 'Имя',
                allowBlank: false,
                name: 'firstName',
                anchor: '100%'
            }, {
                xtype: 'combobox',
                fieldLabel: 'Отдел',
                name: 'department',
                anchor: '100%',
                allowBlank: false,
                store: Ext.create('Ext.data.Store', {
                    model: 'Departments',
                    data: this.departmentList
                }),
                valueField: 'id',
                displayField: 'title',
                typeAhead: true,
                queryMode: 'local',
                emptyText: 'Выберите отдел ...'
            }]
        });
    },
    
    cleanForm: function() {
        var fieldList = this.items.items;
        for (var i = 0; i < fieldList.length; i++) {
            fieldList[i].value = '';
        }
    },
    
    initForm: function(data) {
        var fieldList = this.items.items;
        for (var i = 0; i < fieldList.length; i++) {
            if(data[fieldList[i].name]) {
                fieldList[i].value = data[fieldList[i].name];
            }else {
                fieldList[i].value = '';
            }
        }
    }
    
});
