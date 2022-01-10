package com.example.BalanceService.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.example.BalanceService.dto.BalanceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transitional_balances", indexes = {@Index(name = "ix_account_id", columnList = "accountId")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitionalBalance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer accountId;
	private BigDecimal balanceValue;
	private String operationType;
	
	public TransitionalBalance(BalanceDTO b) {
		
		this.accountId = b.getAccountId();
		this.balanceValue = b.getBalanceValue();
		this.operationType = b.getOperationType();
	}
}
