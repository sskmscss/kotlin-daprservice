/*
 * Copyright (c) Microsoft Corporation.
 * Licensed under the MIT License.
 */
package scm.service

import kotlin.jvm.JvmStatic
import java.lang.Exception

import scm.utility.Utility

/**
 * Message publisher.
 * 1. Build and install jars:
 * mvn clean install
 * 2. Run the program:
 * dapr run --components-path ./components --app-id publisher --dapr-http-port 3006 -- \
 * java -jar examples/target/dapr-java-sdk-examples-exec.jar io.dapr.service.Publisher
 */
object Publisher {
    private const val NUM_MESSAGES = 2

    /**
     * This is the entry point of the publisher app example.
     * @param args Args, unused.
     * @throws Exception A startup Exception.
     */
    @Throws(Exception::class)
    @JvmStatic
    fun mainFunction(args: Array<String>) {

        for (i in 0 until NUM_MESSAGES) {
            val message = String.format("This is message #%d", i)
            //Publishing messages
            Utility.publish(Utility.getConfig()["pubsub"].toString(), Utility.getConfig()["topic"].toString(), message)
        }
    }
}