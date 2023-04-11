package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Lenguage;
import com.example.demo.service.LenguageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/lenguage")
@Endpoint(id="lenguages")
public class LenguageController {

	  @Autowired
	    LenguageService lenguageService;

	    // -------------------Retrieve All Customers--------------------------------------------

	    @GetMapping
	    public ResponseEntity<List<Lenguage>> listAllLenguages() {
	        List<Lenguage> lenguages =  lenguageService.findLenguageAll();

	        return  ResponseEntity.ok(lenguages);
	    }

	    // -------------------Retrieve Single Customer------------------------------------------

	    @GetMapping(value = "/{lenguage}")
	    public ResponseEntity<Lenguage> getLenguage(@PathVariable("lenguage") String _lenguage) {
	        log.info("Fetching Customer with id {}", _lenguage);
	        Lenguage customer = lenguageService.getLenguage(_lenguage);
	        if (  null == customer) {
	            log.error("Customer with id {} not found.", _lenguage);
	            return  ResponseEntity.notFound().build();
	        }
	        return  ResponseEntity.ok(customer);
	    }

	    // -------------------Create a Customer-------------------------------------------

	    @PostMapping
	    public ResponseEntity<Lenguage> createLenguage(@Valid @RequestBody Lenguage lenguage, BindingResult result) {
	        log.info("Creating Customer : {}", lenguage);
	        if (result.hasErrors()){
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
	        }

	        Lenguage customerDB = lenguageService.createLenguage(lenguage);

	        return  ResponseEntity.status( HttpStatus.CREATED).body(customerDB);
	    }

	    // ------------------- Update a Customer ------------------------------------------------

	    @PutMapping
	    public ResponseEntity<Lenguage> updateLenguage(@Valid @RequestBody Lenguage lenguage) {
	        Lenguage currentLenguage = lenguageService.getLenguage(lenguage.getLenguage());

	        System.out.println(currentLenguage);
	        if ( null == currentLenguage ) {
	            log.error("Unable to update. Customer with id {} not found.", lenguage.getLenguage());
	            return  ResponseEntity.notFound().build();
	        }
	        currentLenguage.setText(lenguage.getText());
	        currentLenguage.setLenguage(lenguage.getLenguage());
	        currentLenguage=lenguageService.updateLenguage(currentLenguage);
	        return  ResponseEntity.ok(currentLenguage);
	    }

	    // ------------------- Delete a Customer-----------------------------------------

	    @DeleteMapping(value = "/{lenguage}")
	    public ResponseEntity<Lenguage> deleteLenguage(@PathVariable("lenguage") String _lenguage) {
	        log.info("Fetching & Deleting Customer with id {}", _lenguage);

	        Lenguage lenguage = lenguageService.getLenguage(_lenguage);
	        if ( null == lenguage ) {
	            log.error("Unable to delete. Customer with id {} not found.", _lenguage);
	            return  ResponseEntity.notFound().build();
	        }
	        lenguage = lenguageService.deleteLenguage(lenguage);
	        return  ResponseEntity.ok(lenguage);
	    }

	    private String formatMessage( BindingResult result){
	        List<Map<String,String>> errors = result.getFieldErrors().stream()
	                .map(err ->{
	                    Map<String,String>  error =  new HashMap<>();
	                    error.put(err.getField(), err.getDefaultMessage());
	                    return error;

	                }).collect(Collectors.toList());
	        ErrorMessage errorMessage = ErrorMessage.builder()
	                .code("01")
	                .messages(errors).build();
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonString="";
	        try {
	            jsonString = mapper.writeValueAsString(errorMessage);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return jsonString;
	    }
}
