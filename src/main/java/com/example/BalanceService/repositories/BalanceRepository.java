package com.example.BalanceService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BalanceService.domain.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {

}