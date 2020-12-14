/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package io.dapr.service

import io.dapr.Topic
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import reactor.core.publisher.Mono
import java.lang.Void
import io.dapr.client.domain.CloudEvent
import org.springframework.web.bind.annotation.RequestBody
import java.lang.Exception
import java.lang.RuntimeException

/**
 * SpringBoot Controller to handle input binding.
 */
@RestController
class SubscriberController {
    /**
     * Handles a registered publish endpoint on this app.
     * @param body The body of the http message.
     * @param headers The headers of the http message.
     * @return A message containing the time.
     */
    //@Topic(name = "testingtopic", pubsubName = "messagebus")
    //  @Topic(name = "kotlindapr", pubsubName = "pubsub")
    //@Topic(name = "daprdemo", pubsubName = "messagebus-csharp")
    @Topic(name = "aksKafkaTest", pubsubName = "pubsub")
    @PostMapping(path = ["/aksKafkaTest"]) // @PostMapping(path = "/kotlindapr")
    //@PostMapping(path = "/ageventhub01topic01")
    fun handleMessage(@RequestBody(required = false) body: ByteArray?,
                      @RequestHeader headers: Map<String?, String?>?): Mono<Void> {
        println("CALLED FROM PUBLISHER:::::::::::::::::::::::::")

        return Mono.fromRunnable {
            try {
                // Dapr's event is compliant to CloudEvent.
                val envelope = CloudEvent.deserialize(body)
                val message = if (envelope.data == null) "" else envelope.data
                println("Subscriber got message: $message")
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}