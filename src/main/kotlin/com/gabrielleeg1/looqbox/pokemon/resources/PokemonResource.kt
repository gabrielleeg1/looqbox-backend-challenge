package com.gabrielleeg1.looqbox.pokemon.resources

import com.gabrielleeg1.looqbox.pokemon.domain.PokemonSearchResult
import com.gabrielleeg1.looqbox.pokemon.domain.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("/pokemon")
class PokemonResource(val pokemonService: PokemonService) {
    @GetMapping
    suspend fun search(@RequestParam("q") query: String): PokemonSearchResult {
        val result = pokemonService.queryPokemons(query)

        return PokemonSearchResult(result)
    }
}
