package org.codecraftlabs.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Connection {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://root:secret@localhost:27017/admin?w=authapi")) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }
    }
}
