Ext.define('EmployeesGrid', {
    extend: 'Ext.grid.Panel',
    region: 'center',
    stateful: true,
    stateId: 'stateGrid',
    title: 'Сотрудники',

    initComponent: function() {
        Ext.apply(this, {

            store: Ext.create('Ext.data.Store', {
                model: 'Employees',
                proxy: {
                    type: 'ajax',
                    url: 'rs/employees',
                    reader: {
                        type: 'json',
                        idProperty: 'id.id'
                    },
                    pageParam: undefined,
                    limitParam: undefined,
                    startParam: undefined,
                    noCache: false
                }
            }),
            columns: [
                { header: 'Фамилия', width: 75, sortable: true, dataIndex: 'lastName' },
                { header: 'Имя', width: 75, sortable: true, dataIndex: 'firstName' },
                { header: 'Отдел', width: 75, sortable: true, dataIndex: 'department.title' },
                { header: 'Телефон', width: 100, sortable: true, dataIndex: 'department.phoneNumber' },
                {
                    header: 'Действия',
                    xtype: 'actioncolumn',
                    width: 75,
                    items: [
                        {
                            icon: 'icons/fam/user_edit.png',
                            tooltip: 'Редактировать',
                            handler: function(grid, rowIndex, colIndex) {
                                var rec = grid.store.getAt(rowIndex);
                                alert("edit " + rec.get('id.id'));
                            }
                        },
                        {
                            icon: 'icons/fam/cross.gif',
                            tooltip: 'Удалить',
                            handler: function(grid, rowIndex, colIndex) {
                                var rec = grid.store.getAt(rowIndex);
                                Ext.MessageBox.show({
                                    title: 'Удаление сотрудника',
                                    msg: 'Вы действительно хотите удалить сотрудника?',
                                    buttons: Ext.MessageBox.YESNO,
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

            dockedItems: [{
                xtype: 'toolbar',
                items: [this.addEmployeeAction, this.editEmployeeAction]
            }],

            viewConfig: {
                stripeRows: true,
            }
        });
        this.callParent(arguments);
    }
});

