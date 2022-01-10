package com.example.BalanceService.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.BalanceService.controllers.exceptions.FieldMessage;
import com.example.BalanceService.domain.enums.OperationsType;
import com.example.BalanceService.dto.BalanceDTO;

public class BalanceUpdateValidator implements ConstraintValidator<BalanceUpdate, BalanceDTO>{

	@Override
	public void initialize(BalanceUpdate bu) { }
	
	@Override
	public boolean isValid(BalanceDTO b, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		OperationsType opType = OperationsType.findOpertionType(b.getOperationType());
		
		if (opType == null) {
			
			list.add(new FieldMessage("operationType", "operationType can only are " + OperationsType.names()));
		}
		
		for (FieldMessage e : list) {
			
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

	
}
