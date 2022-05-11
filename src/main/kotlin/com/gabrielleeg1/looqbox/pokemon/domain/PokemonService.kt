package com.gabrielleeg1.looqbox.pokemon.domain

import kotlinx.serialization.Serializable
import org.springframework.stereotype.Service

@Service
interface PokemonService {
    /**
     * Searches pokemons by [query] and returns a list of results.
     *
     * @param query what search for
     * @return a list of pokemons' query result
     */
    suspend fun queryPokemons(query: String): List<QueryResult>
}

/**
 * Results contains the pokemon's name and the highlighted search term.
 *
 * @property name pokemon's name
 * @property start index of the first character of the search term
 * @property start index of the last character of the search term
 */
@Serializable
data class QueryResult(val name: String, val start: Int, val end: Int)
