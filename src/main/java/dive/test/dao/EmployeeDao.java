package dive.test.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.common.base.Preconditions;

import dive.test.entities.DepartmentId;
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

    public Collection<Employee> findAll()
    {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        query.select(query.from(Employee.class));
        return this.entityManager.createQuery(query).getResultList();
    }

    public Collection<Employee> findByDepartment(DepartmentId depId)
    {
        Preconditions.checkArgument(depId != null, "id must not be null");
        Preconditions.checkArgument(depId.getId() != null, "id.getId() must not be null");

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> from = query.from(Employee.class);
        query.select(from);
        query.where(builder.equal(from.get("department").get("id"), depId.getId()));

        return this.entityManager.createQuery(query).getResultList();
    }

    /**
     * Persist employee to DB
     */
    public void persist(Employee employee)
    {
        this.entityManager.persist(employee);
    }
}
