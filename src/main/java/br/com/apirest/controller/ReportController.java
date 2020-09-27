package br.com.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.apirest.service.ReportService;
import br.com.apirest.wrapper.ReportWrapper;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
public class ReportController {
	
   @Autowired
   private ReportService reportService;
   
    
    @ApiOperation(value = "Create a report")
    @GetMapping("/report/customerId")
    public ResponseEntity<ReportWrapper> create(@PathVariable long id) {
    	ReportWrapper report = reportService.report(id);

    	return ResponseEntity.ok(report);

    }
    
   
    
}