package com.example.BalanceService.utils;

import org.springframework.stereotype.Component;

@Component
public class RabbitMQConstants {

	public static final String EXCHANGE = "direct-exchange";
	
	public static final String QUEUE_TOPIC_UPDATE_BALANCE= "account.exchange.topic.queue.update.balance";
	public static final String QUEUE_TOPIC_KEY_UPDATE_BALANCE= "account.exchange.topic.queue.update.balance.*";
}
