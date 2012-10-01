Ext.define('Employees', {
    extend: 'Ext.data.Model',
    fields: ['id.id', 'lastName', 'firstName', 'department.id', 'department.title', 'department.phoneNumber']
});

Ext.define('Departments', {
    extend: 'Ext.data.Model',
    fields: ['id', 'title', 'phoneNumber' ]
});
