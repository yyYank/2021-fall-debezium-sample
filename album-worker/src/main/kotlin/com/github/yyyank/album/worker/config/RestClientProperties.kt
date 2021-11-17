package com.github.yyyank.album.worker.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration

@ConfigurationProperties(prefix = "application.rest.client")
@ConstructorBinding
data class RestClientProperties(
    val maxTotalConnection: Int,
    val maxPerRouteConnection: Int,
    val connectionTtl: Duration,
    val connectionTimeout: Duration,
    val connectionRequestTimeout: Duration,
    val readTimeout: Duration,
)
