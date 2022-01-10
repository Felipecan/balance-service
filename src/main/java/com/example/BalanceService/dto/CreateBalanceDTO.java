package com.example.BalanceService.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBalanceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Cannot be null")
	private Integer accountId;
}
