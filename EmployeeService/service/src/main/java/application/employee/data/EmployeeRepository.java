package application.employee.data;

import application.employee.model.Employee;
import application.employee.model.EmployeeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;


/**
 * Created by admin on 2019/3/3.
 */
@Repository
public class EmployeeRepository
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public Collection<Employee> findEmployees()
    {
        return mongoTemplate.findAll(Employee.class);
    }

    public void createEmployee(Employee employee)
    {
        mongoTemplate.insert(employee);
    }

    public Employee updateEmployee(EmployeeDetail employeeDetail)
    {
        Query query = new Query(where("_id").is(employeeDetail.getId()));
        Update update = new Update().set(Employee.DETAIL_FIELD, employeeDetail);

        return mongoTemplate.findAndModify(query, update, options().returnNew(true), Employee.class);

    }
}
