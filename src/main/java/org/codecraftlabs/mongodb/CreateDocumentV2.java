package org.codecraftlabs.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateDocumentV2 {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://root:secret@localhost:27017/admin")) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("authapi");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");

            insertOneDocument(gradesCollection);
            insertManyDocuments(gradesCollection);
        }
    }

    private static void insertOneDocument(MongoCollection<Document> gradesCollection) {
        gradesCollection.insertOne(generateNewGrade(10000d, 1d));
    }

    private static void insertManyDocuments(MongoCollection<Document> gradesCollection) {
        List<Document> grades = new ArrayList<>();
        for (double classId = 1d; classId <= 10d; classId++) {
            grades.add(generateNewGrade(10001d, classId));
        }

        gradesCollection.insertMany(grades, new InsertManyOptions().ordered(false));
    }

    private static Document generateNewGrade(double studentId, double classId) {
        Random random = new Random();
        List<Document> scores = Arrays.asList(
                new Document("type", "exam").append("score", random.nextDouble() * 100),
                new Document("type", "quiz").append("score", random.nextDouble() * 100),
                new Document("type", "homework").append("score", random.nextDouble() * 100),
                new Document("type", "homework").append("score", random.nextDouble() * 100)
        );

        return new Document("_id", new ObjectId())
                .append("student_id", studentId)
                .append("class_id", classId).append("scores", scores);
    }
}
