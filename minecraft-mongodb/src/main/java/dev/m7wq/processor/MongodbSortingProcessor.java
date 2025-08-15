package dev.m7wq.processor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.Sorts;

import dev.m7wq.configs.HologramConfig;
import dev.m7wq.entity.DocumentEntry;
import dev.m7wq.entity.holograms.Hologram;
import dev.m7wq.entity.holograms.HologramLine;
import dev.m7wq.entity.process.SortingProcessor;
import dev.m7wq.hooks.LuckPermsHook;
import lombok.AllArgsConstructor;

/**
 * MongoDB Sorting Processor 
 * Handles the holograms from the direct received data from mongoDB collection
*/
@AllArgsConstructor
public class MongodbSortingProcessor implements SortingProcessor{

    private Function<Document, DocumentEntry> mapper; 
    private String compareBy;
    private Integer limit;
    private MongoCollection<Document> collection;

    public static MongodbSortingProcessorBuilder builder(){
        return new MongodbSortingProcessorBuilder();
    }


    @Override
    public void process(List<Hologram> holograms, HologramConfig config) {

        holograms.add(new Hologram(config.getHeader(), config));

        FindIterable<Document> iterable = collection
            .find()
            .sort(Sorts.descending(compareBy))
            .limit(limit);


        List<Document> documents = new ArrayList<>();

        for(Document document : iterable){
            documents.add(document);
        }

        while (documents.size() < limit) {
            documents.add(null);
        }


        int rank = 1;

        for (Document document : documents) {

            if (document==null) {

                HologramLine hologramLine = HologramLine.builder()
                    .amount(0)
                    .name("None")
                    .rank(rank).build();

                Hologram hologram = new Hologram(hologramLine, config);

                holograms.add(hologram);
                
                continue;
            }

            DocumentEntry entry = mapper.apply(document);

            HologramLine hologramLine = HologramLine.builder()
                .name(LuckPermsHook.format(entry.getKey(), config))
                .amount(entry.getValue())
                .rank(rank)
                .build();

            holograms.add(new Hologram(hologramLine, config));

            rank++;
            
        }




    
        holograms.add(new Hologram(config.getFooter(), config));
    
    }

    

}
