package com.naya.springbootwithspark.controller;

import com.naya.springbootwithspark.service.WordsSparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WordsSparkController {

    @Autowired
    private WordsSparkService wordsSparkService;

    @GetMapping("/topX/{amount}")
    public Map<String, Integer> topXWordsInFile(@PathVariable int amount, @RequestParam String fileName) {
        return wordsSparkService.topXWordsInFile(amount, fileName);
    }

}
