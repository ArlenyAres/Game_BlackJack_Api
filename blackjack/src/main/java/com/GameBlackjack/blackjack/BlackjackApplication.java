package com.GameBlackjack.blackjack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.GameBlackjack.blackjack.repository")
@EnableReactiveMongoRepositories(basePackages = "com.GameBlackjack.blackjack.repository")
public class BlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
	}

}
