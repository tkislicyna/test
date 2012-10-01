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
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
