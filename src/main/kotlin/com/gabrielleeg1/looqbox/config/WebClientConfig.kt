package com.gabrielleeg1.looqbox.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeFunction
import org.springframework.web.reactive.function.client.ExchangeFunctions

@Configuration
class WebClientConfig {
    @Bean
    fun exchangeFunction(): ExchangeFunction =
        ExchangeFunctions.create(ReactorClientHttpConnector())
}
