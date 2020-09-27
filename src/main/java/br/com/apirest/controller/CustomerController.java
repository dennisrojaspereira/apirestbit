package br.com.apirest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apirest.model.CustomerModel;
import br.com.apirest.repository.CustomerRepository;
import br.com.apirest.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@ResponseBody
public class CustomerController {
	
    private final CustomerService customerService;
    
    @Autowired private CustomerRepository customerRepository;
    
    

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @ApiOperation(value = "Return a list customer")
    @ApiResponses(value = {
    	    @ApiResponse(code = 200, message = "Return list"),
    	    @ApiResponse(code = 403, message = "No access to this item"),
    	    @ApiResponse(code = 500, message = "Exception"),
    	})
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerModel>> getAll(){
        List<CustomerModel> findAll = customerRepository.findAll();
        return ResponseEntity.ok(findAll);
    }
    
    @ApiOperation(value = "Create a customer")
    @PostMapping("/customers")
    public ResponseEntity<Object> create(@RequestBody CustomerModel customer) {
    	CustomerModel saved = customerService.create(customer);

    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(saved.getId()).toUri();

    	return ResponseEntity.created(location).build();

    }
    
    @ApiOperation(value = "Update a customer")
    @PutMapping("/customers/{id}")
    public ResponseEntity<Object> update(@RequestBody CustomerModel customer, @PathVariable long id) {

    	CustomerModel update = customerService.update(customer, id);

    	if (update == null)
    		return ResponseEntity.notFound().build();

    	return ResponseEntity.noContent().build();
    }
    
    
    @ApiOperation(value = "Delete a customer")
    @DeleteMapping("/customers/{id}")
    public void delete(@PathVariable long id) {
    	customerRepository.deleteById(id);
    }
    
    @ApiOperation(value = "Get a customer")
    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> get( @PathVariable long id) {

    	CustomerModel data = customerService.getById(id);
    	if (data == null)
    		return ResponseEntity.notFound().build();

    	return ResponseEntity.ok(data);
    }
    
    
}