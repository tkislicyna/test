package dive.test.rest;

import static org.mockito.Mockito.*;

import org.testng.annotations.Test;

import dive.test.dao.EmployeeDao;
import dive.test.entities.DepartmentId;

/**
 * Created 02.10.2012
 * @author tkislicyna
 * 
**/
public class TestEmployeeService
{
    @Test
    public void testGetEmployeesByDepartment()
    {
        EmployeeDao dao = mock(EmployeeDao.class);
        EmployeeService ems = new EmployeeService();
        ems.setEmployeeDao(dao);
        ems.getEmployees(1);
        verify(dao).findByDepartment(DepartmentId.valueOf(1));
    }

    @Test
    public void testGetAllEmployees()
    {
        EmployeeDao dao = mock(EmployeeDao.class);
        EmployeeService ems = new EmployeeService();
        ems.setEmployeeDao(dao);
        ems.getEmployees(null);
        verify(dao).findAll();
    }

}
