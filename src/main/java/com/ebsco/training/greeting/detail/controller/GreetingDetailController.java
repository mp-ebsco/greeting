package com.ebsco.training.greeting.detail.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.training.greeting.controller.GreetingController;
import com.ebsco.training.greeting.detail.value.GreetingDetail;

@RestController
@RequestMapping("/greeting/{id}/detail/{detailId}")
public class GreetingDetailController {

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<GreetingDetail> getGreetingDetail(@PathVariable("id") Integer id,
            @PathVariable("detailId") Integer detailId) {
        GreetingDetail greetingDetail = new GreetingDetail("Greeting Detail: " + detailId);
        greetingDetail
                .add(linkTo(methodOn(GreetingDetailController.class).getGreetingDetail(id, detailId)).withSelfRel());
        greetingDetail.add(linkTo(methodOn(GreetingController.class).getGreetingById(id)).withRel("greeting"));
        return new HttpEntity<GreetingDetail>(greetingDetail);
    }
}