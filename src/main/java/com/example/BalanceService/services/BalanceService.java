package com.example.BalanceService.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BalanceService.domain.Balance;
import com.example.BalanceService.repositories.BalanceRepository;

@Service
public class BalanceService {

	@Autowired
	private BalanceRepository balanceRepository;
	
	public Balance createBalance(Balance b) {
		
		b.setBalanceValue(BigDecimal.ZERO);
		return balanceRepository.save(b);
	}
	
	public Balance findBalanceFromAccountId(Integer accountId) {
		
		// TODO Fazer o throw
		Optional<Balance> b = balanceRepository.findById(accountId);
		return b.orElse(null);
	}
	
	public Balance updateBalanceOfAccount(Balance b) {
	
		// TODO Certirficar que vai ser lancada exception aqui
		findBalanceFromAccountId(b.getAccountId());
		return balanceRepository.save(b);
	}
}