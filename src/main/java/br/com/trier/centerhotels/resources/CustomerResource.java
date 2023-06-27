package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.services.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerResource extends BaseResource<Customer, Integer, CustomerServiceImpl> {

	 @Autowired
	    public CustomerResource(CustomerServiceImpl service) {
	        super(service);
	    }

	    @Override
	    protected void setEntityId(Customer entity, Integer id) {
	        entity.setId(id);
	    }
}
