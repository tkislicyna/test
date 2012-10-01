package dive.test.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.google.common.base.Preconditions;

import dive.test.entities.Employee;
import dive.test.entities.EmployeeId;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/
@Stateless
public class EmployeeDao
{
    @PersistenceContext
    private EntityManager entityManager;

    public Employee findById(EmployeeId id)
    {
        Preconditions.checkArgument(id != null, "id must not be null");
        Preconditions.checkArgument(id.getId() != null, "id.getId() must not be null");
        return this.entityManager.find(Employee.class, id.getId());
    }

    public void persist(Employee employee)
    {
        this.entityManager.persist(employee);
    }
}
