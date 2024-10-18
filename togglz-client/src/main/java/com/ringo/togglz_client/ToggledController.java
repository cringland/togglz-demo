package com.ringo.togglz_client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ringo.togglz_client.model.FooFeatures;

@RestController
public class ToggledController {

    @GetMapping("/basic-test")
    public String basicTest() {
        if (FooFeatures.FEATURE_ONE.isActive()) {
            return "Hello World";
        } else {
            return "Goodbye World";
        }
    }
}
