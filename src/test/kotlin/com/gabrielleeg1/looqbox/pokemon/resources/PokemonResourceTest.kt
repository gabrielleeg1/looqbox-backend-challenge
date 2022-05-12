package com.gabrielleeg1.looqbox.pokemon.resources

import com.gabrielleeg1.looqbox.pokemon.domain.PokemonSearchResult
import com.gabrielleeg1.looqbox.pokemon.domain.PokemonService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@WebFluxTest
class PokemonResourceTest(
    @Autowired
    @MockBean val pokemonService: PokemonService,
    @Autowired val client: WebTestClient
) {
    @Test
    fun `test should get a pokemon search result`(): Unit = runBlocking {
        `when`(pokemonService.queryPokemons("pik")).thenReturn(emptyList())

        client
            .get().uri("/pokemon?q=pik")
            .exchange()
            .expectStatus().isOk
            .expectBody<PokemonSearchResult>()
            .isEqualTo(PokemonSearchResult(emptyList()))

        verify(pokemonService).queryPokemons("pik")
    }
}
