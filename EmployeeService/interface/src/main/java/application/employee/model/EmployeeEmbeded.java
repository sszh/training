package application.employee.model;

import org.springframework.data.annotation.Transient;

/**
 * Created by admin on 2019/3/3.
 */
public abstract class EmployeeEmbeded
{
    @Transient
    private String id;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
