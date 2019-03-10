package application.employee.model;

/**
 * Created by admin on 2019/3/3.
 */
public class EmployeeDetail extends EmployeeEmbeded
{
    private String firstname;
    private String lastname;

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
}
