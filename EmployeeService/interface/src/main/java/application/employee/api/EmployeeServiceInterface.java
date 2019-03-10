package application.employee.api;

import application.employee.model.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by admin on 2019/2/24.
 */
@Path("/employee")
public interface EmployeeServiceInterface
{
    /**
     * Add new Employee
     * @param employee
     */
    @POST
    @Path("/employees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addEmployee(Employee employee);

    /**
     * Get all of the employee info
     * @return
     */
    @GET
    @Path("/employees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Employee> getEmployees();

    @PUT
    @Path("/employees/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee updateEmployee(@PathParam("id") String id, Employee employee);

    @DELETE
    @Path("/employees/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEmployee(@PathParam("id") String id);
}
