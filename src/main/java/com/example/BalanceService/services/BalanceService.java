package com.example.BalanceService.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BalanceService.domain.Balance;
import com.example.BalanceService.domain.TransitionalBalance;
import com.example.BalanceService.repositories.BalanceRepository;
import com.example.BalanceService.services.exceptions.ObjectNotFoundException;

@Service
public class BalanceService {

	@Autowired
	private BalanceRepository repository;
	
	@Autowired
	private TransitionalBalanceService transitionalBalanceService;
	
	public Balance createBalance(Balance b) {
				
		b.setBalanceValue(BigDecimal.ZERO);
		
		Optional<Balance> balance = repository.findById(b.getAccountId());
		b = balance.orElse(b);
		
		return repository.save(b);
	}
	
	public Balance findBalanceFromAccountId(Integer accountId) {
		
		Optional<Balance> b = repository.findById(accountId);
		return b.orElseThrow(() -> new ObjectNotFoundException(
				"Balance for ID " + accountId + " not found!"));
	}
	
	
	public Balance getRealBalanceFromAccountId(Integer accountId) {
		
		Balance balance = findBalanceFromAccountId(accountId);
		
		List<TransitionalBalance> transitionalBalances = transitionalBalanceService
				.getAllTransitionalBalanceFromAccount(accountId);

		return balance.calculateRealbalance(transitionalBalances);
	}
	
	public Balance updateBalanceOfAccount(Balance b) {
			
		findBalanceFromAccountId(b.getAccountId());
		return repository.save(b);
	}
}