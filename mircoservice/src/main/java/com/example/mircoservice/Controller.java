package com.example.mircoservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private int score;

    @GetMapping("/score")
    public Integer getScore() {
        return ++score;
    }


}
