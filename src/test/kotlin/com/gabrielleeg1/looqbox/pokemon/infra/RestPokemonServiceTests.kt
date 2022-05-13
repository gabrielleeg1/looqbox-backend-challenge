package com.gabrielleeg1.looqbox.pokemon.infra

import com.gabrielleeg1.looqbox.pokemon.domain.NamedAPIResource
import com.gabrielleeg1.looqbox.pokemon.domain.PokemonResult
import com.gabrielleeg1.looqbox.pokemon.domain.QueryResult
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFunction
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
            onBlocking { bodyToMono(any<ParameterizedTypeReference<PokemonResult>>()) } doReturn
                Mono.just(PokemonResult(0, null, null, emptyList()))
        }

        `when`(exchangeFunction.exchange(any())).thenReturn(Mono.just(response))

        val queryResults = restPokemonService.queryPokemons("pik")

        assertThat(queryResults.size).isEqualTo(0)

        verify(response).bodyToMono(any<ParameterizedTypeReference<PokemonResult>>())
    }

    @Test
    fun `test should sort correctly the pokemons queried`(): Unit = runBlocking {
        val pokemons = listOf(
            NamedAPIResource("pidgeot", "?"),
            NamedAPIResource("pidgeotto", "?"),
            NamedAPIResource("pidgey", "?"),
        )

        val expectedResults = listOf(
            QueryResult("pidgeot", "<pre>pid</pre>geot"),
            QueryResult("pidgeotto", "<pre>pid</pre>geotto"),
            QueryResult("pidgey", "<pre>pid</pre>gey"),
        )

        val response = mock<ClientResponse> {
            onBlocking { bodyToMono(any<ParameterizedTypeReference<PokemonResult>>()) } doReturn
                Mono.just(PokemonResult(0, null, null, pokemons))
        }

        `when`(exchangeFunction.exchange(any())).thenReturn(Mono.just(response))

        val queryResults = restPokemonService.queryPokemons("pid")

        assertThat(queryResults.size).isEqualTo(3)
        assertThat(queryResults).isEqualTo(expectedResults)

        verify(response).bodyToMono(any<ParameterizedTypeReference<PokemonResult>>())
    }
}
