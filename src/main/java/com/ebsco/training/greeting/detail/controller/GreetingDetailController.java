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
@RequestMapping("/greeting/{id}/detail")
public class GreetingDetailController {

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<GreetingDetail> getGreetingDetail(@PathVariable("id") Integer id) {
        GreetingDetail greeting = new GreetingDetail("Awesome details about our greeting");
        greeting.add(linkTo(methodOn(GreetingDetailController.class).getGreetingDetail(id)).withSelfRel());
        greeting.add(linkTo(methodOn(GreetingController.class).getGreetingById(id)).withRel("greeting"));
        return new HttpEntity<GreetingDetail>(greeting);
    }
}
