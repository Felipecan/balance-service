package com.example.BalanceService.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.example.BalanceService.domain.Balance;
import com.example.BalanceService.services.validation.BalanceUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@BalanceUpdate
public class BalanceDTO extends CreateBalanceDTO {
	
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Cannot be null")
	private BigDecimal balanceValue;
	
	@NotNull(message = "Cannot be null")
	private String operationType;
}
