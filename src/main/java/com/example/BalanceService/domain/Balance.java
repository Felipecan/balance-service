package com.example.BalanceService.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.BalanceService.domain.enums.OperationsType;
import com.example.BalanceService.dto.CreateBalanceDTO;

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
	
	public Balance(CreateBalanceDTO b) {
		
		this.accountId = b.getAccountId();
	}
	
	public Balance(TransitionalBalance tb) {
		
		this.accountId = tb.getAccountId();
		this.balanceValue = tb.getBalanceValue();
	}
	
	public Balance calculateRealbalance(List<TransitionalBalance> transitionalBalances) {

		BigDecimal value = this.balanceValue;

		for (TransitionalBalance tb : transitionalBalances) {

			OperationsType opertionsType = OperationsType.findOpertionType(tb.getOperationType());
			value = opertionsType.performOperation(value, tb.getBalanceValue());
		}

		return new Balance(this.accountId, value);
	}
}
