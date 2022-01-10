package com.example.BalanceService.domain.enums;

import java.math.BigDecimal;

import org.apache.commons.lang3.EnumUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationsType {

	DEBIT('-') {
	
		public BigDecimal performOperation(BigDecimal a, BigDecimal b) {
			return a.subtract(b);
		}
	},
	
	CREDIT('+'){
	
		public BigDecimal performOperation(BigDecimal a, BigDecimal b) {
			return a.add(b);
		}
	},
	;
	
	private char symbol;
	
	public abstract BigDecimal performOperation(BigDecimal a, BigDecimal b);
	
	public static OperationsType findOpertionType(String opType) {
		
		OperationsType operationType = null;
		
		for (OperationsType ot : OperationsType.values()) {
			
			if (ot.name().equals(opType)) {
				
				operationType = ot;
				break;
			}
		}
		
		return operationType;
	}
	
	public static String names() {
				
	    return EnumUtils.getEnumMap(OperationsType.class).keySet().toString();
	}
}
