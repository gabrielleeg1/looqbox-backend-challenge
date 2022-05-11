package com.gabrielleeg1.looqbox.pokemon.infra

import com.gabrielleeg1.looqbox.pokemon.domain.PokemonService
import com.gabrielleeg1.looqbox.pokemon.domain.QueryResult
import org.springframework.stereotype.Service

@Service
class RestPokemonService : PokemonService {
    override suspend fun queryPokemons(query: String): List<QueryResult> {
        TODO("Not yet implemented")
    }
}
