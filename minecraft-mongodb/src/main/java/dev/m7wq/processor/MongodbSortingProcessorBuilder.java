package dev.m7wq.processor;

import java.util.function.Function;

import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

import dev.m7wq.entity.DocumentEntry;

import org.bson.Document;

public class MongodbSortingProcessorBuilder {

    private Function<Document, DocumentEntry> mapper;
    private String compareBy;
    private Integer limit;
    private MongoCollection<Document> collection;

    public MongodbSortingProcessorBuilder mapper(Function<Document, DocumentEntry> mapper) {
        this.mapper = mapper;
        return this;
    }

    public MongodbSortingProcessorBuilder compareBy(String compareBy) {
        this.compareBy = compareBy;
        return this;
    }

    public MongodbSortingProcessorBuilder limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public MongodbSortingProcessorBuilder collection(MongoCollection<Document> collection) {
        this.collection = collection;
        return this;
    }

    public MongodbSortingProcessor build() {
        return new MongodbSortingProcessor(mapper, compareBy, limit, collection);
    }
}
