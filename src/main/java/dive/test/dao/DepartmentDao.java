package dive.test.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.google.common.base.Preconditions;

import dive.test.entities.Department;
import dive.test.entities.DepartmentId;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/
@Stateless
public class DepartmentDao
{
    @PersistenceContext
    private EntityManager entityManager;

    public Department findById(DepartmentId id)
    {
        Preconditions.checkArgument(id != null, "id must not be null");
        Preconditions.checkArgument(id.getId() != null, "id.getId() must not be null");
        return this.entityManager.find(Department.class, id.getId());
    }

    public Collection<Department> findAll()
    {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
        query.select(query.from(Department.class));
        return this.entityManager.createQuery(query).getResultList();
    }

    /**
     * Persist department to DB
     */
    public void persist(Department department)
    {
        this.entityManager.persist(department);
    }
}
