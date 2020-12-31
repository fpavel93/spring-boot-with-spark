package com.naya.springbootwithspark.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileSparkService {
    @Autowired
    private JavaSparkContext sc;

    public JavaRDD<String> rddFromFile(String filePath){
        return sc.textFile(filePath);
    }
}
