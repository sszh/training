package application.employee.service;

import application.employee.config.EmployeeContextConfig;
import application.employee.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by admin on 2019/2/24.
 */
public class TestEmployeeService
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(EmployeeContextConfig.class);
        EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);
        //MOCK DATA
        Employee employee = new Employee();
        employee.setCode("002");
        employee.setName("Jim");
        employeeService.addEmployee(employee);
    }
}
