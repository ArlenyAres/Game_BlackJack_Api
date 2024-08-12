package com.GameBlackjack.blackjack.controller;


import com.GameBlackjack.blackjack.model.Player;
import com.GameBlackjack.blackjack.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Tag("SavePlayer Controller Test")
    public void testSavePlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Test Player");

        when(playerService.savePlayer(any(Player.class))).thenReturn(Mono.just(player));


        Mono<Player> result = playerController.createPlayer(player);

        StepVerifier.create(result)
                .expectNext(player)
                .verifyComplete();

    }

    @Test
    @Tag("ControllerTest")
    public void testGetPlayer_Success() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Test Player");

        when(playerService.getPlayer(anyLong())).thenReturn(Mono.just(player));

        Mono<Player> result = playerController.getPlayer(1L);

        StepVerifier.create(result)
                .expectNext(player)
                .verifyComplete();
    }


    @Test
    @Tag("ControllerTest Get All Players")
    public void testGetAllPlayers() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setName("Test Player");

        Player player2 = new Player();
        player2.setId(2L);
        player2.setName("Test Player 2");

        when(playerService.getAllPlayers()).thenReturn(Flux.just(player1, player2));

        Flux<Player> result = playerController.getAllPlayers();

        StepVerifier.create(result)
                .expectNext(player1)
                .expectNext(player2)
                .verifyComplete();


    }

    @Test
    public void testUpdatePlayerName_Success() {
        Player existingPlayer = new Player();
        existingPlayer.setId(1L);
        existingPlayer.setName("Test Player");

        Player updatedPlayer = new Player();
        updatedPlayer.setName("Jane Doe");

        when(playerService.getPlayer(anyLong())).thenReturn(Mono.just(existingPlayer));
        when(playerService.savePlayer(any(Player.class))).thenReturn(Mono.just(existingPlayer));

        Mono<Player> result = playerController.updatePlayerName(1L, updatedPlayer);

        StepVerifier.create(result)
                .expectNext(existingPlayer)
                .verifyComplete();
    }


}
