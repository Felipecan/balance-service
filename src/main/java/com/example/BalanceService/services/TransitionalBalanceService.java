package com.example.BalanceService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BalanceService.domain.TransitionalBalance;
import com.example.BalanceService.repositories.TransitionalBalanceRepository;
import com.example.BalanceService.services.exceptions.ObjectNotFoundException;

@Service
public class TransitionalBalanceService {
	
	@Autowired
	private TransitionalBalanceRepository repository;
	
	@Autowired
	RabbitMQService rabbitService;
	
	public TransitionalBalance findTransitionalBalanceById(Integer id) {
		
		Optional<TransitionalBalance> tb = repository.findById(id);
		return tb.orElseThrow(() -> new ObjectNotFoundException(
				"Transitional balance ID " + id + " not found!"));
	}
	
	public List<TransitionalBalance> getAllTransitionalBalanceFromAccount(Integer accountId) {
	
		return repository.findByAccountId(accountId); 
	}

	public TransitionalBalance updateBalanceOfAccount(TransitionalBalance tb) {

		tb.setId(null);		
		tb = repository.save(tb);
		
		rabbitService.insertIntoUpdateBalanceQueue(tb);
		
		return tb;
	}
	
	public void deleteTransitionalBalanceById(Integer id) {
		
		findTransitionalBalanceById(id);
		repository.deleteById(id);
	}
}
