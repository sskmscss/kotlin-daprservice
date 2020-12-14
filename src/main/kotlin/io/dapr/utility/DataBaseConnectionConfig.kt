package io.dapr.utility

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection


object DataBaseConnectionConfig {
    var mongoClient: MongoClient? = null

    fun getMongoClientInstance(): MongoClient? {
        if(mongoClient == null){
            val connectionString = ConnectionString(Utility.getUtilitySecret("azurekeyvault", "deliverymomentdbapi").toString()  + "&retrywrites=false")
            val mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build()
            mongoClient = MongoClients.create(mongoClientSettings)
        }

        return mongoClient
    }


    fun mongoCollection(): MongoCollection<org.bson.Document> {
        return getMongoClientInstance()!!.getDatabase("deliverymoment-db").getCollection("delivery-moment\u200b")
    }
}