package com.GameBlackjack.blackjack.service;

import com.GameBlackjack.blackjack.exception.ResourceNotFoundException;
import com.GameBlackjack.blackjack.model.Game;
import com.GameBlackjack.blackjack.model.Player;
import com.GameBlackjack.blackjack.repository.GameRepository;
import com.GameBlackjack.blackjack.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;

    public Mono<Game> createGame(List<Long> playerIds) {
        return Flux.fromIterable(playerIds)
                .flatMap(playerService::getPlayer)
                .collectList()
                .flatMap(players -> {
                    Game game = new Game();
                    game.startGame(players);
                    return gameRepository.save(game);
                });
    }

    public Mono<Game> getGame(String id) {
        return gameRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Game not found")));
    }

    public Mono<Player> playerHit(String gameId, Long playerId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Game not found")))
                .flatMap(game -> {
                    Player player = game.getPlayers().stream()
                            .filter(p -> p.getId() == (playerId))
                            .findFirst()
                            .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + playerId));

                    game.playerHit(player);
                    return gameRepository.save(game).thenReturn(player);
                });
    }


    public Mono<Void> playerStand(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Game not found")))
                .flatMap(game -> {
                    game.playerStand();
                    return gameRepository.save(game).then();
                });
    }

    public Mono<Player> placeBet(String gameId, Long playerId, int amount) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Game not found")))
                .flatMap(game -> {
                    Player player = game.getPlayers().stream()
                            .filter(p -> p.getId() == playerId)
                            .findFirst()
                            .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + playerId));

                    player.setBet(amount);
                    return gameRepository.save(game).then(playerService.updatePlayerBet(playerId, amount)).thenReturn(player);                });
    }


    public Mono<Void> dealerTurn(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Game not found")))
                .flatMap(game -> {
                    game.getDealer().playTurn(game.getDeck());
                    return gameRepository.save(game).then();
                });
    }

    public Mono<List<Player>> determineWinners(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Game not found")))
                .map(game -> {
                    List<Player> winners = game.determineWinners();
                    winners.forEach(playerRepository::save);
                    return winners;
                });
    }

    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.deleteById(gameId);
    }
}
