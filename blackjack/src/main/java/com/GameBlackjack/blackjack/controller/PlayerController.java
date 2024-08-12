package com.GameBlackjack.blackjack.controller;

import com.GameBlackjack.blackjack.model.Player;
import com.GameBlackjack.blackjack.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/player")
@Tag(name = "Player Management System", description = "Operations pertaining to players in Blackjack")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/add")
    @Operation(summary = "Add a new player", description = "Add a new player to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public Mono<Player> createPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get player by id", description = "Get a player by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the player"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    public Mono<Player> getPlayer(@PathVariable Long id) {
        return playerService.getPlayer(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all players", description = "Get all players in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all players"),
            @ApiResponse(responseCode = "404", description = "No players found")
    })
    public Flux<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PutMapping("/{playerId}")
    @Operation(summary = "Update player name", description = "Update the name of a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player name updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    public Mono<Player> updatePlayerName(@PathVariable Long playerId, @RequestBody Player updatedPlayer) {
        return playerService.getPlayer(playerId)
                .flatMap(existingPlayer -> {
                    existingPlayer.setName(updatedPlayer.getName());
                    return playerService.savePlayer(existingPlayer);
                });
    }

}
