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
    'Ext.state.*'
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

    // create the Grid
    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        stateId: 'stateGrid',
        columns: [
            {
                header     : 'Фамилия',
                width    : 75,
                sortable : true,
                dataIndex: 'lastName'
            },
            {
                header     : 'Имя',
                width    : 75,
                sortable : true,
                dataIndex: 'firstName'
            },
            {
                header     : 'Отдел',
                width    : 75,
                sortable : true,
                dataIndex: 'department'
            },
            {
                header     : 'Телефон',
                width    : 75,
                sortable : true,
                dataIndex: 'phoneNumber'
            },
            {
                header: 'Действия',
                xtype: 'actioncolumn',
                width: 75,
                items: [  
                    {
                        icon   : 'icons/fam/user_edit.png',
                        tooltip: 'Редактировать',
                        handler: function(grid, rowIndex, colIndex) {
                            var rec = store.getAt(rowIndex);
                            alert("edit " + rec.get('id'));
                        }
                    },
                    {
                        icon   : 'icons/fam/cross.gif', 
                        tooltip: 'Удалить',
                        handler: function(grid, rowIndex, colIndex) {
                            var rec = store.getAt(rowIndex);
                            alert("delete " + rec.get('id'));
                        }
                    }
                ]
            }
        ],
        title: 'Сотрудники',
        renderTo: 'grid',
        viewConfig: {
            stripeRows: true
        }
    });
});

