Ext.define('Employees', {
    extend: 'Ext.data.Model',
    fields: ['id', 'lastName', 'firstName', 'deparmentId', 'department', 'phoneNumber']
});

Ext.define('Departments', {
    extend: 'Ext.data.Model',
    fields: ['id', 'title', 'phoneNumber' ]
});
