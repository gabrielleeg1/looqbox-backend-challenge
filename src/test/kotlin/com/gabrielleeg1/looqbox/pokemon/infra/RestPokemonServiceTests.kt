package com.gabrielleeg1.looqbox.pokemon.infra

import com.gabrielleeg1.looqbox.pokemon.domain.PokemonResult
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doReturnConsecutively
import org.mockito.kotlin.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@SpringBootTest
class RestPokemonServiceTests(
    @Autowired
    @MockBean val exchangeFunction: ExchangeFunction,
    @Autowired val restPokemonService: RestPokemonService,
) {
    @Test
    fun `test should query pokemons with rest pokemon service`(): Unit = runBlocking {
        val response = mock<ClientResponse> {
            onBlocking { bodyToMono(PokemonResult::class.java) } doReturn
                Mono.just(PokemonResult(0, null, null, emptyList()))
        }

        `when`(exchangeFunction.exchange(any())).thenReturn(Mono.just(response))

        val queryResults = restPokemonService.queryPokemons("pik")

        assertThat(queryResults.size).isEqualTo(0)

        verify(response).bodyToMono<PokemonResult>()
    }

    @Test
    fun `test should query pokemons with rest pokemon service until the next is null`(): Unit =
        runBlocking {
            val response = mock<ClientResponse> {
                onBlocking { bodyToMono(PokemonResult::class.java) } doReturnConsecutively listOf(
                    Mono.just(PokemonResult(0, "next", null, emptyList())),
                    Mono.just(PokemonResult(0, null, null, emptyList()))
                )
            }

            `when`(exchangeFunction.exchange(any())).thenReturn(Mono.just(response))

            val queryResults = restPokemonService.queryPokemons("pik")

            assertThat(queryResults.size).isEqualTo(0)

            verify(response, times(2)).bodyToMono<PokemonResult>()
        }
}
