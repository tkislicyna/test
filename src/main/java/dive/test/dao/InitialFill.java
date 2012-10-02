package dive.test.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dive.test.entities.Department;
import dive.test.entities.Employee;

/**
 * Class for loading test data to DB
 * <p>Created 01.10.2012
 * @author tkislicyna
 * 
**/
@Startup
@Singleton
public class InitialFill
{
    private static final Logger log = LoggerFactory.getLogger(InitialFill.class);

    @Inject
    private DepartmentDao departmentDao;

    @Inject
    private EmployeeDao employeeDao;

    @PostConstruct
    public void fillInitialData()
    {
        Department a = createDepartment("Отдел A", "333-55-55");
        Department b = createDepartment("Отдел B", "363-05-50");
        Department c = createDepartment("Отдел C", "332-02-02");

        createEmployee("Петр", "Петров", a);
        createEmployee("Василий", "Сидоров", a);
        createEmployee("Иван", "Иванов", a);

        createEmployee("Юрий", "Гагарин", b);
        createEmployee("Герман", "Титов", b);
        createEmployee("Семен", "Савельев", b);
        createEmployee("Влад", "Петров", b);

        createEmployee("Герман", "Зайцев", c);
        createEmployee("Маша", "Зайцева", c);
        createEmployee("Оля", "Зайцева", c);
        createEmployee("Даша", "Зайцева", c);
        createEmployee("Галя", "Зайцева", c);
    }

    private Department createDepartment(String title,
                                        String phoneNumber)
    {
        Department department = new Department();
        department.setTitle(title);
        department.setPhoneNumber(phoneNumber);

        this.departmentDao.persist(department);
        log.info("Created {}", department);
        return department;
    }

    private Employee createEmployee(String firstName,
                                    String lastName,
                                    Department department)
    {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment(department);

        this.employeeDao.persist(employee);
        log.info("Created {}", employee);
        return employee;
    }
}
