package application.employee.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2019/2/24.
 */
@Deprecated
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>
{
}
