package com.ebsco.training.greeting.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.training.greeting.detail.controller.GreetingDetailController;
import com.ebsco.training.greeting.value.Greeting;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Greeting> getGreetingById(@PathVariable("id") Integer id) {
        Greeting greeting = new Greeting(greetingsById.get(id));
        System.out.println("ID: " + id);
        greeting.add(linkTo(methodOn(GreetingController.class).getGreetingById(id)).withSelfRel());
        greeting.add(linkTo(methodOn(GreetingDetailController.class).getGreetingDetail(id)).withRel("detail"));
        return new HttpEntity<Greeting>(greeting);
    }
    
    private static Map<Integer, String> greetingsById = new HashMap<>();
    
    static {
        greetingsById.put(1, "Greetings earthlings, we shall now take over your planet");
        greetingsById.put(2, "Greetings and salutations friends");
    }
    
}
