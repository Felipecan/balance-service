package com.example.BalanceService.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BalanceService.domain.Balance;
import com.example.BalanceService.repositories.BalanceRepository;
import com.example.BalanceService.services.exceptions.ObjectNotFoundException;

@Service
public class BalanceService {

	@Autowired
	private BalanceRepository repository;
	
	public Balance createBalance(Balance b) {
				
		b.setBalanceValue(BigDecimal.ZERO);
		return repository.save(b);
	}
	
	public Balance findBalanceFromAccountId(Integer accountId) {
		
		Optional<Balance> b = repository.findById(accountId);
		return b.orElseThrow(() -> new ObjectNotFoundException(
				"Balance for ID " + accountId + " not found!"));
	}
	
	public Balance updateBalanceOfAccount(Balance b) {
	
		findBalanceFromAccountId(b.getAccountId());
		return repository.save(b);
	}
}