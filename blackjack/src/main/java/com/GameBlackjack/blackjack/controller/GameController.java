package com.GameBlackjack.blackjack.controller;

import com.GameBlackjack.blackjack.model.Game;
import com.GameBlackjack.blackjack.model.Player;
import com.GameBlackjack.blackjack.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/game")
@Tag(name = "Game Management System", description = "Operations pertaining to Blackjack games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/new")
    @Operation(summary = "Create a new game", description = "Create a new Blackjack game with the specified players.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public Mono<Game> createGame(@RequestBody List<Long> playerIds) {
        return gameService.createGame(playerIds);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get game details", description = "Retrieve the details of a specific game by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game found"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public Mono<Game> getGame(@PathVariable String id) {
        return gameService.getGame(id);
    }

    @PostMapping("/{id}/hit")
    @Operation(summary = "Player hits", description = "Record a hit action for the current player in the specified game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hit recorded successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public Mono<Void> playerHit(@PathVariable String id, @RequestParam Long playerId) {
        return gameService.playerHit(id, playerId);
    }

    @PostMapping("/{id}/stand")
    @Operation(summary = "Player stands", description = "Record a stand action for the current player in the specified game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stand recorded successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public Mono<Void> playerStand(@PathVariable String id) {
        return gameService.playerStand(id);
    }

    @PostMapping("/{id}/bet")
    @Operation(summary = "Player places a bet", description = "Record a bet placed by a player in the specified game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bet placed successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public Mono<Void> placeBet(@PathVariable String id, @RequestParam Long playerId, @RequestParam int amount) {
        return gameService.placeBet(id, playerId, amount);
    }

    @PostMapping("/{id}/dealer")
    @Operation(summary = "Dealer's turn", description = "Initiate the dealer's turn in the specified game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dealer's turn initiated"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public Mono<Void> dealerTurn(@PathVariable String id) {
        return gameService.dealerTurn(id);
    }

    @GetMapping("/{id}/winners")
    @Operation(summary = "Determine winners", description = "Determine the winners of the specified game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Winners determined successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public Mono<List<Player>> determineWinners(@PathVariable String id) {
        return gameService.determineWinners(id);
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Delete a game", description = "Delete the specified game.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    public Mono<Void> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id);
    }
}
