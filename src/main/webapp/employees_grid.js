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
                { header: 'Телефон', width: 100, sortable: true, dataIndex: 'department.phoneNumber' }
            ],
            viewConfig: {
                stripeRows: true,
            }
        });
        this.callParent(arguments);
    }
});

