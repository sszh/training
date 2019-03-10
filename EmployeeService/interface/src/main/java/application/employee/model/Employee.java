package application.employee.model;

import org.springframework.data.mongodb.core.mapping.Document;
import platform.persistence.model.ModelBase;

/**
 * Created by admin on 2019/2/24.
 */
@Document(collection = "Employee")
public class Employee extends ModelBase
{
    private String code;
    private String name;

    private EmployeeDetail employeeDetail;
    public static final String DETAIL_FIELD = "employeeDetail";

    //Default Constructor
    public Employee()
    {

    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public EmployeeDetail getEmployeeDetail()
    {
        return employeeDetail;
    }

    public void setEmployeeDetail(EmployeeDetail employeeDetail)
    {
        this.employeeDetail = employeeDetail;
    }
}
