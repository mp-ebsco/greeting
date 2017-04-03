package com.ebsco.training.greeting.detail.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.training.greeting.controller.GreetingController;
import com.ebsco.training.greeting.detail.domain.GreetingDetail;
import com.ebsco.training.greeting.domain.Greeting;

@RestController
@RequestMapping("/greeting/{id}/detail")
public class GreetingDetailController {

    // Get Greeting Detail
    @RequestMapping(value="/{detailId}", method = RequestMethod.GET)
    public HttpEntity<GreetingDetail> getGreetingDetail(@PathVariable("id") Integer id,
            @PathVariable("detailId") Integer detailId) {
        GreetingDetail greetingDetail = createGreetingDetail(id, detailId);
        return new HttpEntity<GreetingDetail>(greetingDetail);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<HttpEntity<GreetingDetail>> getGreetingDetails(@PathVariable("id") Integer id) {
        
        return IntStream.range(1, 3).mapToObj(detailId -> new HttpEntity<GreetingDetail>(createGreetingDetail(id, detailId)))
                .collect(Collectors.toList());
    }
    
    private GreetingDetail createGreetingDetail(Integer id, Integer detailId) {
        GreetingDetail greetingDetail = new GreetingDetail(detailId, "Greeting Detail: " + detailId);
        greetingDetail
                .add(linkTo(methodOn(GreetingDetailController.class).getGreetingDetail(id, detailId)).withSelfRel());
        greetingDetail.add(linkTo(methodOn(GreetingController.class).getGreetingById(id)).withRel("greeting"));
        return greetingDetail;
    }
}