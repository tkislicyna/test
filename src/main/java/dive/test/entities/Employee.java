package dive.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/
@Entity
@Table
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String firstName;

    private String lastName;

    @ManyToOne
    private Department department;

    public EmployeeId getId()
    {
        return EmployeeId.valueOf(this.id);
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Department getDepartment()
    {
        return this.department;
    }

    public void setDepartment(Department department)
    {
        this.department = department;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
