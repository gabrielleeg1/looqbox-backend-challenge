package com.gabrielleeg1.looqbox.pokemon.infra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class PokemonConfig(val exchangeFunction: ExchangeFunction) {
    @Bean("pokemonClient")
    fun pokemonClient(): WebClient = WebClient.builder()
        .exchangeFunction(exchangeFunction)
        .baseUrl("https://pokeapi.co/api/v2/")
        .build()
}
