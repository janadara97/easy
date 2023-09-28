package com.easy.notification.easynotification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class EasyNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyNotificationApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){
		log.info("Received the notification for Order - {}",orderPlacedEvent.getOrderNumber());
	}
}
