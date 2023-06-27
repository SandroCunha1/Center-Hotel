package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.services.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeResource extends BaseResource<Employee, Integer, EmployeeServiceImpl> {

    @Autowired
    public EmployeeResource(EmployeeServiceImpl service) {
        super(service);
    }

    @Override
    protected void setEntityId(Employee entity, Integer id) {
        entity.setId(id);
    }
}