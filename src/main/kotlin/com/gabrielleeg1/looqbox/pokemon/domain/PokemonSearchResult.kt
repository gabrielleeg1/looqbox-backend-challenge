package com.gabrielleeg1.looqbox.pokemon.domain

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSearchResult(val result: List<QueryResult>)
