package com.example.BalanceService.controllers;

import java.net.URI;

import javax.validation.Valid;

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
import com.example.BalanceService.domain.TransitionalBalance;
import com.example.BalanceService.dto.CreateBalanceDTO;
import com.example.BalanceService.dto.BalanceDTO;
import com.example.BalanceService.services.BalanceService;
import com.example.BalanceService.services.TransitionalBalanceService;

@RestController
@RequestMapping(value = "/balances")
public class BalanceController {
	
	@Autowired
	private BalanceService service;
	
	@Autowired
	private TransitionalBalanceService transitionalService;

	@PostMapping
	public ResponseEntity<?> createBalance(@Valid @RequestBody CreateBalanceDTO b) {
		
		Balance balance = new Balance(b);
		balance = service.createBalance(balance);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(balance.getAccountId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/{accountId}")
	public ResponseEntity<?> getBalanceFromAccount(@PathVariable Integer accountId) {
		
		Balance b = service.getRealBalanceFromAccountId(accountId);
		return ResponseEntity.ok().body(b);
	}
	
	@PutMapping(value = "/{accountId}")
	public ResponseEntity<?> updateBalanceOfAccount(@Valid @RequestBody BalanceDTO b, @PathVariable Integer accountId) {
	
		b.setAccountId(accountId);
		TransitionalBalance tb = new TransitionalBalance(b);
		
		tb = transitionalService.updateBalanceOfAccount(tb);
		
		return ResponseEntity.noContent().build();
	}
}
