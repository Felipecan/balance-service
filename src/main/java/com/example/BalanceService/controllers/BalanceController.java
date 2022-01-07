package com.example.BalanceService.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.BalanceService.domain.Balance;
import com.example.BalanceService.services.BalanceService;

@RestController
@RequestMapping(value = "/balances")
public class BalanceController {
	
	@Autowired
	private BalanceService service;

	@PostMapping
	public ResponseEntity<?> createBalance(@RequestBody Balance b) {
		
		b = service.createBalance(b);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(b.getAccountId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{accountId}")
	public ResponseEntity<?> getBalanceFromAccount(@PathVariable Integer accountId) {
		
		Balance b = service.findBalanceFromAccountId(accountId);
		return ResponseEntity.ok().body(b);
	}
	
	@PutMapping(value = "/{accountId}")
	public ResponseEntity<?> updateBalanceOfAccount(@RequestBody Balance b, @PathVariable Integer accountId) {
	
		b.setAccountId(accountId);
		b = service.updateBalanceOfAccount(b);
		return ResponseEntity.noContent().build();
	}
}
