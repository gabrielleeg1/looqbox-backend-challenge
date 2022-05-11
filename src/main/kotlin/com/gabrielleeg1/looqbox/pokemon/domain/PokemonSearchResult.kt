package com.gabrielleeg1.looqbox.pokemon.domain

import kotlinx.serialization.Serializable

@Serializable
class PokemonSearchResult(val result: List<QueryResult>)
