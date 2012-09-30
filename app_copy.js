/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.tab.*',
    'Ext.window.*',
    'Ext.layout.container.Border',
    'Ext.Action',
    'Ext.tip.*',
]);

Ext.onReady(function() {
    Ext.QuickTips.init();
    
    // setup the state provider, all state information will be saved to a cookie
    Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));

    // sample static data for the store
    var myData = [
        ['id', 'Петров', 'Петр', 'А', '+8913794949'],
        ['id', 'Петров', 'Петр', 'А', '+8913794949'],
        ['id', 'Петров', 'Петр', 'А', '+8913794949'],
        ['id', 'Петров', 'Петр', 'А', '+8913794949']
    ];

    // create the data store
    var store = Ext.create('Ext.data.ArrayStore', {
        fields: [
            {name: 'id'},
            {name: 'lastName'},
            {name: 'firstName'},
            {name: 'department'},
            {name: 'phoneNumber'}
        ],
        data: myData
    });
    //create dialog and form for add user
    var form = Ext.create('Ext.form.Panel', {
        url: 'save-form.php',
        defaultType: 'textfield',
        border: false,
        bodyPadding: 7,
        fieldDefaults: { labelWidth:60 },
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
            store: Ext.create('Ext.data.ArrayStore', {
                fields: ['abbr', 'state'],
                data: [
                    ['AL', 'Alabama' ],
                    ['AK', 'Alaska' ]
                ]
            }),
            valueField: 'abbr',
            displayField: 'state',
            typeAhead: true,
            queryMode: 'local',
            emptyText: 'Выберите отдел ...'
        }]
    });
    
    var win = Ext.create('widget.window', {
        title: 'Добавить сотрудника',
        closable: true,
        closeAction: 'hide',
        width: 270,
        minWidth: 230,
        height: 170,
        layout: 'fit',
        plain:true,
        items: form,
        buttons: [{
            text: 'Сохранить'
        },{
            text: 'Отмена'
        }]
    });    

    var addEmployeeAction = Ext.create('Ext.Action', {
        iconCls: 'add-user',
        text: 'Добавить сотрудника',
        handler: function(widget, event) {
            win.show();
        }
    });

    var contextMenu = Ext.create('Ext.menu.Menu', {
        items: [
            addEmployeeAction
        ]
    });
    
    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        stateId: 'stateGrid',
        columns: [
            {
                header: 'Фамилия',
                width: 75,
                sortable: true,
                dataIndex: 'lastName'
            },
            {
                header: 'Имя',
                width: 75,
                sortable: true,
                dataIndex: 'firstName'
            },
            {
                header: 'Отдел',
                width: 75,
                sortable: true,
                dataIndex: 'department'
            },
            {
                header: 'Телефон',
                width: 75,
                sortable: true,
                dataIndex: 'phoneNumber'
            },
            {
                header: 'Действия',
                xtype: 'actioncolumn',
                width: 75,
                items: [  
                    {
                        icon: 'icons/fam/user_edit.png',
                        tooltip: 'Редактировать',
                        handler: function(grid, rowIndex, colIndex) {
                            var rec = store.getAt(rowIndex);
                            alert("edit " + rec.get('id'));
                        }
                    },
                    {
                        icon: 'icons/fam/cross.gif', 
                        tooltip: 'Удалить',
                        handler: function(grid, rowIndex, colIndex) {
                            var rec = store.getAt(rowIndex);
                            Ext.MessageBox.show({
                                title: 'Удаление сотрудника',
                                msg: 'Вы действительно хотите удалить сотрудника?',
                                buttons:Ext.MessageBox.YESNO,
                                fn: function(btn)
                                {
                                    alert(btn);
                                    //alert("delete " + rec.get('id'));
                                }
                            });
                        }
                    }
                ]
            }
        ],
        title: 'Сотрудники',
       
        dockedItems: [{
            xtype: 'toolbar',
            items: [
                addEmployeeAction
            ]
        }],
        viewConfig: {
            stripeRows: true
        }
    });
    // departments panel
    var departmentForm = Ext.create('Ext.form.Panel', {
        url: 'save-form.php',
        defaultType: 'textfield',
        border: false,
        bodyPadding: 7,
        fieldDefaults: { labelWidth: 100},
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

    var departmentWin = Ext.create('widget.window', {
        title: 'Добавить отдел',
        closable: true,
        closeAction: 'hide',
        width: 270,
        minWidth: 230,
        height: 170,
        layout: 'fit',
        plain:true,
        items: departmentForm,
        buttons: [{
            text: 'Сохранить'
        },{
            text: 'Отмена'
        }]
    });
    
    var addDepartmentAction = Ext.create('Ext.Action', {
        iconCls: 'add-button',
        text: 'Добавить отдел',
        handler: function(widget, event) {
            departmentWin.show();
        }
    });
    
    var deps =  Ext.create('Ext.panel.Panel', {
        dockedItems: [{
            xtype: 'toolbar',
            items: [ addDepartmentAction ]
        }]
    });

    //layout
    Ext.create('Ext.panel.Panel', {
        height: 350,
        anchor: '100%',
        layout: 'border',
        bodyStyle: 'padding: 5px;',
        renderTo: 'grid',
        items: [{
            region: 'west',
            title: 'Отделы',
            width: 250,
            split: true,
            collapsible: true,
            floatable: false,
            items: deps
        }, {
            region: 'center',
            items: grid
        }]
    });
    
});

