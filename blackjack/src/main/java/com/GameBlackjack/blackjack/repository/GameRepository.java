package com.GameBlackjack.blackjack.repository;

import com.GameBlackjack.blackjack.model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, String>{

}
