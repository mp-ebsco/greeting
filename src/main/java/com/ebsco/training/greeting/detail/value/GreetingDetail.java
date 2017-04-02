package com.ebsco.training.greeting.detail.value;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

public class GreetingDetail extends ResourceSupport {
    
    private String detail;

    @JsonCreator
    public GreetingDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
