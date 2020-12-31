package com.naya.springbootwithspark.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WordsSparkService {

    @Autowired
    private JavaSparkContext sc;
    @Autowired
    private List<String> forbiddenWords;

    @Autowired
    private FileSparkService fileSparkService;
    @Autowired
    private CountingWordsSparkService countingWordsSparkService;

    private Broadcast<List<String>> forbiddenWordsBroadcast;

    @EventListener(ContextRefreshedEvent.class)
    public void broadcastListsToExecutors(){
        forbiddenWordsBroadcast = sc.broadcast(forbiddenWords);
    }

    public Map<String, Integer> topXWordsInFile(int amount, String fileName){
        JavaRDD<String> rdd = fileSparkService.rddFromFile(fileName);
        return countingWordsSparkService.findTopXWordsInFile(rdd, amount, forbiddenWordsBroadcast);
    }
}
