package com.example.BalanceService.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "balances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer accountId;
	private BigDecimal balanceValue;
}
