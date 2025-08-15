import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

import dev.m7wq.entity.DocumentEntry;
import dev.m7wq.processor.MongodbSortingProcessor;

public class Test {

    public static void main(String[] args) {

        MongoCollection<Document> collection = null; // implement your collection
    
        MongodbSortingProcessor mongodbSortingProcessor = MongodbSortingProcessor.builder()
            .mapper(
                document-> DocumentEntry.builder()
                    .key(document.getString("name"))
                    .value(document.getInteger("points"))
                    .build()
            )
            .compareBy("points")
            .collection(collection)
            .limit(10)
            .build();

        
        
    }
}
