package com.example.BalanceService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BalanceService.domain.Balance;
import com.example.BalanceService.domain.TransitionalBalance;

@Repository
public interface TransitionalBalanceRepository extends JpaRepository<TransitionalBalance, Integer> {

	public List<TransitionalBalance> findByAccountId(Integer accountId);
}