package com.example.BalanceService.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BalanceService.domain.TransitionalBalance;
import com.example.BalanceService.utils.RabbitMQConstants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RabbitMQService {

	@Autowired
	private final RabbitTemplate rabbitMQ;
	
	public void insertIntoUpdateBalanceQueue(TransitionalBalance tb) {
		
		rabbitMQ.convertAndSend(RabbitMQConstants.EXCHANGE, 
				RabbitMQConstants.QUEUE_TOPIC_KEY_UPDATE_BALANCE, 
				tb);
	}
}
