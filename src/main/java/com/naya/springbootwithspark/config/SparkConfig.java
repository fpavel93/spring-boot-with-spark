package com.naya.springbootwithspark.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
    @Bean
    public SparkConf sparkConf(@Value("${spark.appname}") String appName, @Value("${spark.master}") String sparkMaster){
        return new SparkConf().setAppName(appName).setMaster(sparkMaster);
    }

    @Bean
    public JavaSparkContext javaSparkContext(SparkConf sparkConf) {
        return new JavaSparkContext(sparkConf);
    }
}
