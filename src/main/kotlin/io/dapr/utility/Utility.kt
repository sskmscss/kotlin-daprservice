package io.dapr.utility

import io.dapr.client.DaprClient
import io.dapr.client.DaprClientBuilder

object Utility {

    var client: DaprClient? = null

    fun getClientInstance(): DaprClient? {

        if(client == null){
            client = DaprClientBuilder().build()
        }

        return client
    }

    fun publish(PUBSUB_NAME: String, TOPIC_NAME: String, message: String) {
        getClientInstance()?.publishEvent(PUBSUB_NAME, TOPIC_NAME, message)?.block()
        println("Published message: $message")

//        message.stream().forEach { getClientInstance()?.publishEvent(PUBSUB_NAME, TOPIC_NAME, message)?.block() }

        try {
            Thread.sleep((1000 * Math.random()).toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
            return
        }
    }

    fun getUtilitySecret(secretStore: String, secretKey: String): String? {
        println("client called::")
        val client : DaprClient = DaprClientBuilder().build()

        println(client)
        var mapParams: MutableMap<String, String> = mutableMapOf<String, String>()
        mapParams.put("metadata.namespace", "edppublic-deliverymomentcrud-dev")

//        var secret = client.getSecret(secretStore,secretKey).block()
        var secret = client.getSecret(secretStore,secretKey, mapParams).block()
        println(secret?.get(secretKey.toString()))
        return secret?.get(secretKey.toString())
    }


}