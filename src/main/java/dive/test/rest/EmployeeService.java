package dive.test.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import dive.test.dao.EmployeeDao;
import dive.test.entities.DepartmentId;
import dive.test.entities.Employee;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/
@Path("employees")
public class EmployeeService
{
    private EmployeeDao employeeDao;

    @Inject
    public void setEmployeeDao(EmployeeDao employeeDao)
    {
        this.employeeDao = employeeDao;
    }

    @GET
    @Produces(MediaTypes.JSON_UTF_8)
    public Collection<Employee> getEmployees(@QueryParam("departmentId") Integer departmentId)
    {
        if (departmentId == null)
        {
            return this.employeeDao.findAll();
        }
        return this.employeeDao.findByDepartment(DepartmentId.valueOf(departmentId));
    }
}