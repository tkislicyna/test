package dive.test.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
