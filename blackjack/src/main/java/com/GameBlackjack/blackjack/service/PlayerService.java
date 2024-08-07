package com.GameBlackjack.blackjack.service;

import com.GameBlackjack.blackjack.exception.ResourceNotFoundException;
import com.GameBlackjack.blackjack.model.Player;
import com.GameBlackjack.blackjack.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Mono<Player> savePlayer(Player player) {
        return Mono.just(playerRepository.save(player));
    }

    public Mono<Player> getPlayer(Long id) {
        return Mono.justOrEmpty(playerRepository.findById(id))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Player not found")));
    }

    public Flux<Player> getAllPlayers() {
        return Flux.fromIterable(playerRepository.findAll());
    }

    public Mono<Player> updatePlayer(Player player) {
        return Mono.just(playerRepository.save(player));
    }

    public Mono<Void> updatePlayerBet(Long playerId, int bet) {
        return Mono.justOrEmpty(playerRepository.findById(playerId))
                .flatMap(player -> {
                    player.setBet(bet);
                    playerRepository.save(player);
                    return Mono.empty();
                });
    }

    public Mono<Void> updatePlayerScore(Long playerId, int score) {
        return Mono.justOrEmpty(playerRepository.findById(playerId))
                .flatMap(player -> {
                    player.setScore(score);
                    playerRepository.save(player);
                    return Mono.empty();
                });
    }
}