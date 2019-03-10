package application.employee.service;

import application.employee.api.EmployeeServiceInterface;
import application.employee.model.Employee;
import application.employee.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 2019/2/24.
 */
@Service
public class EmployeeService implements EmployeeServiceInterface
{
    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee)
    {
        employeeRepository.createEmployee(employee);
    }

    public Collection<Employee> getEmployees()
    {
        return employeeRepository.findEmployees();
    }

    public Employee updateEmployee(String id, Employee employee)
    {
        employee.getEmployeeDetail().setId(id);//123
        return employeeRepository.updateEmployee(employee.getEmployeeDetail());
    }

    public void deleteEmployee(String id)
    {

    }

}
