package com.example.kafka

import io.kotest.core.spec.style.StringSpec
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class KafkaProducerTest : StringSpec({
    "KafkaProducer" {
        val producer = KafkaProducer<String, String>(Properties().also {
            it[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            it[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            it[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:50956"
        })

        Thread.sleep(5_000)

        println(producer.partitionsFor("my-topic"))

        Thread.sleep(60_000)
    }
})
