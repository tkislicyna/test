package dive.test.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dive.test.dao.DepartmentDao;
import dive.test.entities.Department;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/
@Path("departments")
public class DepartmentService
{
    @Inject
    private DepartmentDao depDao;

    @GET
    @Produces(MediaTypes.JSON_UTF_8)
    public Collection<Department> getAllDepartments()
    {
        return this.depDao.findAll();
    }
}
