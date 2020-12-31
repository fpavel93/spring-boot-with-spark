package com.naya.springbootwithspark.service;

import com.google.common.collect.Iterables;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountingWordsSparkService {

    public Map<String, Integer> findTopXWordsInFile(JavaRDD<String> rdd, int amount, Broadcast<List<String>> forbiddenWords) {
        return rdd.flatMap(x -> Arrays.asList(x.split(" ")).iterator())
                .filter(s -> !forbiddenWords.value().contains(s))
                .map(String::toLowerCase)
                .groupBy(s -> s)
                .mapValues(Iterables::size)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .mapToPair(Tuple2::swap)
                .take(amount)
                .stream().collect(Collectors.toMap(Tuple2::_1,Tuple2::_2));
    }
}
