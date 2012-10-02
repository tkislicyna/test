package dive.test.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created 01.10.2012
 * @author tkislicyna
 * 
**/

public class DepartmentId
{
    private Integer id;

    private DepartmentId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return this.id;
    }

    public static DepartmentId valueOf(Integer id)
    {
        return new DepartmentId(id);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
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
            if (obj instanceof Integer)
            {
                return obj.equals(this.id);
            } else
            {
                return false;
            }
        }
        DepartmentId other = (DepartmentId) obj;
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
