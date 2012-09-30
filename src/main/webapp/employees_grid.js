Ext.define('EmployeesGrid', {
    extend: 'Ext.grid.Panel',
    region: 'center',
    stateful: true,
    stateId: 'stateGrid',
    title: 'Сотрудники',

    initComponent: function() {
        var grid = this;

        Ext.apply(this, {

            store: Ext.create('Ext.data.Store', {
                model: 'Employees',
                data: [
                    { id: 1, lastName: 'Петров', firstName: 'Петр', department: 'А', phoneNumber: '+79137949491' },
                    { id: 2, lastName: 'Иванов', firstName: 'Петр', department: 'А', phoneNumber: '+79137949492' },
                    { id: 3, lastName: 'Сидоров', firstName: 'Василий', department: 'А', phoneNumber: '+79137949493' },
                    { id: 4, lastName: 'Гармаш', firstName: 'Сергей', department: 'А', phoneNumber: '+79137949494' },
                ] // TODO
            }),
            columns: [
                { header: 'Фамилия', width: 75, sortable: true, dataIndex: 'lastName' },
                { header: 'Имя', width: 75, sortable: true, dataIndex: 'firstName' },
                { header: 'Отдел', width: 75, sortable: true, dataIndex: 'department' },
                { header: 'Телефон', width: 100, sortable: true, dataIndex: 'phoneNumber' },
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
                                alert("edit " + rec.get('id'));
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

