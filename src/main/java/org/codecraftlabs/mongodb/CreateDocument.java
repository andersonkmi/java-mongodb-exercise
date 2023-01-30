package org.codecraftlabs.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Random;

import static java.util.Arrays.asList;

public class CreateDocument {
    public static void main(String[] args) {
        System.out.println("Starting");
        try (MongoClient mongoClient = MongoClients.create("mongodb://root:secret@localhost:27017/admin")) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("authapi");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");

            Random rand = new Random();
            Document student = new Document("_id", new ObjectId());
            student.append("student_id", 10000d)
                    .append("class_id", 1d)
                    .append("scores", asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                            new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100)));

            gradesCollection.insertOne(student);
        }
    }
}
