package com.github.yyyank.kafka

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class TracksConsumer {
    private val log = LoggerFactory.getLogger(TracksConsumer::class.java)
    val objectMapper: ObjectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @KafkaListener(topics = ["dbserver1.debezium-sample.tracks"], groupId = "tracks")
    fun subscribeTracks(event: ConsumerRecord<String, String>) {
        log.info("[START]subscribe change data capture table --- tracks", event)
        val key = objectMapper.readValue(event.key(), Key::class.java)
        val value = objectMapper.readValue(event.value(), Value::class.java)
        when (value.payload.op) {
            "c" -> log.info("insert tracks")
            "u" -> log.info("update tracks")
            "d" -> log.info("delete tracks")
            // FIXME 業務で書くと後々困りそうなコードだよ
            else -> log.info("what's happen?", value.payload.op)
        }
        log.info("changed record id:${key.payload.id}", event)
        log.info("before:${value.payload.before}", event)
        log.info("after:${value.payload.after}", event)
        log.info("[END]subscribe change data capture table --- tracks", event)
    }


    data class Key(
        val schema: KeyStruct = KeyStruct(),
        val payload: KeyPayload = KeyPayload()
    ) {
        data class KeyStruct(
            val type: String = "",
            val fields: List<KeyField> = emptyList(),
            val optional: Boolean = false,
            val name: String = "",
        )

        data class KeyField(
            val type: String = "",
            val optional: Boolean = false,
            val field: String = "",
        )

        data class KeyPayload(val id: Int = 0)
    }

    data class Value(
        val schema: ValueStruct = ValueStruct(),
        val payload: ValuePayloadHolder = ValuePayloadHolder()
    ) {
        data class ValueStruct(
            val type: String = "",
            val fields: List<ValueField> = emptyList(),
            val optional: Boolean = false,
            val name: String = "",
        )

        data class ValueField(
            val type: String = "",
            val optional: Boolean = false,
            val field: String = "",
            val name: String? = null,
            val version: Int? = null,
            val default: String? = null,
            val fields: Any? = null
        )

        data class ValuePayloadHolder(
            val before: ValuePayload? = null,
            val after: ValuePayload = ValuePayload(),
            val op: String = "",
            // val source: Any = Any(),
            // val ts_ms : BigInteger = BigInteger.ZERO,
            // val transaction: Any = Any()
        )

        data class ValuePayload(
            val id: Int = 0,
            val no: Int = 0,
            val name: String = "",
            val review: String = "",
            val created_at: String = ""
        )
    }

}
