package br.com.apirest.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apirest.model.PurchaseModel;
import br.com.apirest.repository.PurchaseRepository;
import br.com.apirest.service.PurchaseService;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
public class PurchaseController {
	
   @Autowired
   private PurchaseService purchaseService;
   
   @Autowired
   private PurchaseRepository purchaseRepository;   
    
    @ApiOperation(value = "Create a purchase")
    @PostMapping("/purchases")
    public ResponseEntity<Object> create(@RequestBody PurchaseModel purchaseModel) {
    	PurchaseModel saved = purchaseService.create(purchaseModel);
    	
    	if ( saved == null) {
    		return new ResponseEntity<Object>("Don't have money in balance to buy",HttpStatus.CONFLICT);
    	}

    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(saved.getId()).toUri();

    	return ResponseEntity.created(location).build();

    }
    
   
    
    @ApiOperation(value = "Get a Purchase")
    @GetMapping("/purchases/{id}")
    public ResponseEntity<Object> get( @PathVariable long id) {

    	Optional<PurchaseModel> data = purchaseRepository.findById(id);
    	if (!data.isPresent())
    		return ResponseEntity.notFound().build();

    	return ResponseEntity.ok(data);
    }
    
    
}