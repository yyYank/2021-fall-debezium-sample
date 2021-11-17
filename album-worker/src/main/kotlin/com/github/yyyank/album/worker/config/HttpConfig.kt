package com.github.yyyank.album.worker.config

import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.client.RestTemplateCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
@EnableConfigurationProperties(
    RestClientProperties::class,
)
class HttpConfig(
    private val clientProperties: RestClientProperties
) {

    @Bean
    fun restTemplateCustomizer(): RestTemplateCustomizer {
        return SimpleRestTemplateCustomizer(
            clientProperties.maxTotalConnection,
            clientProperties.maxPerRouteConnection,
            clientProperties.connectionTtl,
            clientProperties.connectionTimeout,
            clientProperties.connectionRequestTimeout,
            clientProperties.readTimeout,
        )
    }

    @Bean
    fun restTemplate(restTemplateCustomizer: RestTemplateCustomizer): RestTemplate {
        return RestTemplateBuilder(restTemplateCustomizer)
            .rootUri("http://localhost:8080")
            .build()
    }

    private class SimpleRestTemplateCustomizer(
        val maxTotalConnection: Int,
        val maxPerRouteConnection: Int,
        val connectionTtl: Duration,
        val connectionTimeout: Duration,
        val connectionRequestTimeout: Duration,
        val readTimeout: Duration,
    ) : RestTemplateCustomizer {

        override fun customize(restTemplate: RestTemplate) {
            restTemplate.requestFactory = clientHttpRequestFactory()
        }

        private fun clientHttpRequestFactory(): ClientHttpRequestFactory {
            val httpClient = HttpClientBuilder.create()
                .disableCookieManagement()
                .setConnectionTimeToLive(connectionTtl.toSeconds(), TimeUnit.SECONDS)
                .setMaxConnPerRoute(maxPerRouteConnection)
                .setMaxConnTotal(maxTotalConnection)
                .build()

            return HttpComponentsClientHttpRequestFactory().apply {
                setHttpClient(httpClient)
                setConnectTimeout(connectionTimeout.toMillis().toInt())
                setConnectionRequestTimeout(connectionRequestTimeout.toMillis().toInt())
                setReadTimeout(readTimeout.toMillis().toInt())
            }
        }
    }
}
