package dive.test.rest;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.Collection;

import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

import dive.test.dao.DepartmentDao;
import dive.test.entities.Department;

/**
 * Created 02.10.2012
 * @author tkislicyna
 * 
**/
public class TestDepartmentService
{
    @Test
    public void testGetAllDepartments()
    {
        DepartmentDao departmentDao = mock(DepartmentDao.class);

        DepartmentService departmentService = new DepartmentService();
        departmentService.setDepartmentDao(departmentDao);

        when(departmentDao.findAll()).thenReturn(ImmutableList.<Department> of());

        Collection<Department> deps = departmentService.getAllDepartments();

        verify(departmentDao).findAll();
        assertNotNull(deps);
        assertEquals(deps.size(), 0);
    }
}
