package br.com.trier.centerhotels.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	    
	    @GetMapping("/user/{user}")
	    public ResponseEntity<List<Customer>> findByUserStartsWithIgnoreCase(@PathVariable String user) {
	        return ResponseEntity.ok(service.findByUserStartsWithIgnoreCase(user));
	    }
	    
	    @GetMapping("/name/{name}")
	    public ResponseEntity<List<Customer>> findByNameStartsWithIgnoreCase(@PathVariable String name) {
	        return ResponseEntity.ok(service.findByNameStartsWithIgnoreCase(name));
	    }
	    
	    @GetMapping("/email/{email}")
	    public ResponseEntity<List<Customer>> findByEmailStartsWithIgnoreCase(@PathVariable String email) {
	        return ResponseEntity.ok(service.findByEmailStartsWithIgnoreCase(email));
	    }
	    
	    @GetMapping("/cep/{cep}")
	    public ResponseEntity<List<Customer>> findByCepStartsWith(@PathVariable Integer cep) {
	        return ResponseEntity.ok(service.findByCep(cep));
	    }
	    
	    @GetMapping("/cpf/{cpf}")
	    public ResponseEntity<List<Customer>> findByCepStartsWith(@PathVariable String cpf) {
	        return ResponseEntity.ok(service.findByCpf(cpf));
	    }
	    
	    @GetMapping("/cep-name/{cpf}/{name}")
	    public ResponseEntity<List<Customer>> findByCpfStartsWithAndNameStartsWithIgnoreCase(@PathVariable Integer cpf, @PathVariable String name) {
	        return ResponseEntity.ok(service.findByCepAndNameStartsWithIgnoreCase(cpf, name));
	    }
	    
	    
}
