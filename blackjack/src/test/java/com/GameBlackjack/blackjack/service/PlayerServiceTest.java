package com.GameBlackjack.blackjack.service;


import com.GameBlackjack.blackjack.model.Player;
import com.GameBlackjack.blackjack.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Test Player");

        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Mono<Player> result = playerService.savePlayer(player);

        StepVerifier.create(result)
                .expectNext(player)
                .verifyComplete();
    }


    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setName("Test Player");

        Player player2 = new Player();
        player2.setId(2L);
        player2.setName("Test Player 2");

        when(playerRepository.findAll()).thenReturn(List.of(player1, player2));

        Flux<Player> result = playerService.getAllPlayers();

        StepVerifier.create(result)
                .expectNext(player1, player2)
                .verifyComplete();
    }
}
