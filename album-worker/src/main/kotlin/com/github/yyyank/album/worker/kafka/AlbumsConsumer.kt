package com.github.yyyank.album.worker.kafka

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.yyyank.album.worker.http.AlbumApiHttpClient
import com.github.yyyank.album.worker.http.CreateAlbumsRequest
import com.github.yyyank.album.worker.http.DeleteAlbumsRequest
import com.github.yyyank.album.worker.http.UpdateAlbumsRequest
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Component
class AlbumsConsumer(val albumApiHttpClient: AlbumApiHttpClient) {
    private val log = LoggerFactory.getLogger(AlbumsConsumer::class.java)
    val objectMapper: ObjectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    @KafkaListener(topics = ["dbserver1.debezium-sample.albums"], groupId = "albums")
    fun subscribeAlbums(event: ConsumerRecord<String?, String?>) {
        log.info("[START]subscribe change data capture table --- albums", event)
        if (event.key() == null || event.value() == null) {
            log.info("[END]subscribe change data capture table --- albums", event)
            return
        }
        val key = objectMapper.readValue(event.key(), Key::class.java)
        val value = objectMapper.readValue(event.value(), Value::class.java)
        when (value.payload.op) {
            "c" -> {
                log.info("insert albums")
                albumApiHttpClient.createAlbums(
                    CreateAlbumsRequest(
                        id = value.payload.after?.id ?: error("illegal state"),
                        name = value.payload.after.name,
                        createdAt = LocalDateTime.parse(
                            value.payload.after.created_at,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        )
                    )
                )
            }
            "u" -> {
                log.info("update albums")
                albumApiHttpClient.updateAlbums(
                    UpdateAlbumsRequest(
                        id = value.payload.after?.id ?: error("illegal state"),
                        name = value.payload.after.name,
                        createdAt = LocalDateTime.parse(
                            value.payload.after.created_at,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        )
                    )
                )
            }
            "d" -> {
                log.info("delete albums")
                albumApiHttpClient.deleteAlbums(
                    DeleteAlbumsRequest(
                        id = value.payload.before?.id ?: error("invalid payload")
                    )
                )
            }
            // FIXME 業務で書くと後々困りそうなコードだよ
            else -> log.info("what's happen?", value.payload.op)
        }
        log.info("changed record id:${key.payload.id}", event)
        log.info("before:${value.payload.before}", event)
        log.info("after:${value.payload.after}", event)
        log.info("[END]subscribe change data capture table --- albums", event)
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
            val after: ValuePayload? = null,
            val op: String = "",
            // val source: Any = Any(),
            // val ts_ms : BigInteger = BigInteger.ZERO,
            // val transaction: Any = Any()
        )

        data class ValuePayload(
            val id: Int = 0,
            val name: String = "",
            val created_at: String = ""
        )
    }

}
