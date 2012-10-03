package dive.test.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.base.Objects;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/

public class EmployeeId
{
    private Integer id;

    private EmployeeId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return this.id;
    }

    public static EmployeeId valueOf(Integer id)
    {
        return new EmployeeId(id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        EmployeeId other = (EmployeeId) obj;
        return Objects.equal(this.id, other.id);
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
