package com.ebsco.training.greeting.domain;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Greeting extends ResourceSupport {
    
    private Integer greetingId;
    private String text;

    @JsonCreator
    public Greeting(Integer greetingId, String text) {
        this.greetingId = greetingId;
        this.text = text;
    }

    public Integer getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(Integer greetingId) {
        this.greetingId = greetingId;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}