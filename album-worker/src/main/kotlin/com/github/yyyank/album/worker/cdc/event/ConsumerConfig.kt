package com.github.yyyank.album.worker.cdc.event

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.listener.ListenerExecutionFailedException
import org.springframework.retry.policy.NeverRetryPolicy
import org.springframework.retry.support.RetryTemplate
import org.springframework.retry.support.RetryTemplateBuilder

@Configuration
class KafkaConsumerConfig {

    private val log = LoggerFactory.getLogger(KafkaConsumerConfig::class.java)

    @Bean
    fun kafkaListenerContainerFactory(
        configurer: ConcurrentKafkaListenerContainerFactoryConfigurer,
        kafkaConsumerFactory: ObjectProvider<ConsumerFactory<Any, Any>>
    ): ConcurrentKafkaListenerContainerFactory<*, *> {
        val factory = ConcurrentKafkaListenerContainerFactory<Any, Any>()
        factory.setRetryTemplate(retryTemplate())
        factory.setRecoveryCallback { context ->
            if (context != null) {
                val t = context.lastThrowable
                val errorMessage = context.lastThrowable.message
                val retryCount = context.retryCount
                val groupId = if (t is ListenerExecutionFailedException) {
                    t.groupId
                } else {
                    ""
                }
                log.warn("kafka consumer error. message: $errorMessage, retryCount: $retryCount groupId: $groupId")
            } else {
                log.error("kafka consumer error.")
            }
        }
        configurer.configure(factory, kafkaConsumerFactory.ifAvailable)
        return factory
    }

    private fun retryTemplate(): RetryTemplate = RetryTemplateBuilder()
        .customPolicy(NeverRetryPolicy())
        .build()
}

