package br.com.apirest.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apirest.model.TransferModel;
import br.com.apirest.repository.TransferRepository;
import br.com.apirest.service.TransferService;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
public class TransferController {
	
   @Autowired
   private TransferService transferService;
   
   @Autowired
   private TransferRepository transferRepository;   
    
    @ApiOperation(value = "Create a transfer")
    @PostMapping("/transfers")
    public ResponseEntity<Object> create(@RequestBody TransferModel transferModel) {
    	TransferModel saved = transferService.create(transferModel);

    	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(saved.getId()).toUri();

    	return ResponseEntity.created(location).build();

    }
    
   
    
    @ApiOperation(value = "Get a Transfer")
    @GetMapping("/transfers/{id}")
    public ResponseEntity<Object> get( @PathVariable long id) {

    	Optional<TransferModel> data = transferRepository.findById(id);
    	if (!data.isPresent())
    		return ResponseEntity.notFound().build();

    	return ResponseEntity.ok(data);
    }
    
    
}