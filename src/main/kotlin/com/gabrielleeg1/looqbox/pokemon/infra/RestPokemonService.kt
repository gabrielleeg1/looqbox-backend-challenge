package com.gabrielleeg1.looqbox.pokemon.infra

import com.gabrielleeg1.looqbox.pokemon.domain.PokemonResult
import com.gabrielleeg1.looqbox.pokemon.domain.PokemonService
import com.gabrielleeg1.looqbox.pokemon.domain.QueryResult
import com.gabrielleeg1.looqbox.utils.bubbleSort
import com.gabrielleeg1.looqbox.utils.quickSort
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class RestPokemonService(val pokemonClient: WebClient) : PokemonService {
    override suspend fun queryPokemons(query: String): List<QueryResult> {
        val resources = pokemonClient
            .get().uri("/pokemon?limit=1000000&offset=0")
            .retrieve()
            .bodyToMono<PokemonResult>()
            .map { it.results }
            .awaitSingle()

        return resources
            .map { it.name }
            .filter { it.contains(query) }
            .bubbleSort(compareBy { it.length })
            .quickSort()
            .map { QueryResult(it, it.replace(query, "<pre>$query</pre>")) }
    }
}
