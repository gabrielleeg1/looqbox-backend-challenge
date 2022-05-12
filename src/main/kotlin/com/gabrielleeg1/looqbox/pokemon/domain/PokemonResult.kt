package com.gabrielleeg1.looqbox.pokemon.domain

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedAPIResource>,
)

@Serializable
data class NamedAPIResource(val name: String, val url: String)
