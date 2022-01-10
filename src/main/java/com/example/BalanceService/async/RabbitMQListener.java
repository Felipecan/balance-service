package com.example.BalanceService.async;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.BalanceService.domain.Balance;
import com.example.BalanceService.domain.TransitionalBalance;
import com.example.BalanceService.services.BalanceService;
import com.example.BalanceService.services.RabbitMQService;
import com.example.BalanceService.services.TransitionalBalanceService;
import com.example.BalanceService.utils.RabbitMQConstants;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQListener {

	@Autowired
	private RabbitMQService rabbitService;
	
	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private TransitionalBalanceService transitionalBalanceService;
	
	@RabbitListener(queues = RabbitMQConstants.QUEUE_TOPIC_UPDATE_BALANCE)
	public void updateBalance(@Payload TransitionalBalance tb) {
				
		try {
			
			Balance balance = balanceService.findBalanceFromAccountId(tb.getAccountId());
			
			List<TransitionalBalance> transitionalBalances = Lists.newArrayList(tb);

			Balance newBalance = balance.calculateRealbalance(transitionalBalances);

			balanceService.updateBalanceOfAccount(newBalance);
			transitionalBalanceService.deleteTransitionalBalanceById(tb.getId());

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			rabbitService.insertIntoUpdateBalanceQueue(tb);
		}
	}
}
