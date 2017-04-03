package com.ebsco.training.greeting.detail.domain;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

public class GreetingDetail extends ResourceSupport {
    
    private Integer detailId;
    private String detail;

    @JsonCreator
    public GreetingDetail(Integer detailId, String detail) {
        this.detailId = detailId;
        this.detail = detail;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
