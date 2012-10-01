package dive.test.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/

@Entity
@Table
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    private String phoneNumber;

    public DepartmentId getId()
    {
        return DepartmentId.valueOf(this.id);
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
