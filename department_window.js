Ext.define('DepartmentWindow', {
    extend: 'Ext.window.Window',
    title: 'Добавить отдел',
    closable: true,
    closeAction: 'hide',
    width: 270,
    minWidth: 230,
    height: 170,
    layout: 'fit',
    plain: true,
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
            fieldDefaults: { labelWidth: 100 },
            items: [{
                fieldLabel: 'Наименование',
                allowBlank: false,
                name: 'departmentName',
                anchor: '100%'
            }, {
                fieldLabel: 'Телефон',
                allowBlank: false,
                name: 'phoneNumber',
                anchor: '100%'
            }]
        });
    }
});
