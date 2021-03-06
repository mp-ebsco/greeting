package com.ebsco.training.greeting.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ebsco.training.greeting.detail.controller.GreetingDetailController;
import com.ebsco.training.greeting.domain.Greeting;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // Get greeting by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Greeting> getGreetingById(@PathVariable("id") Integer id) {
        Greeting greeting = createGreeting(id);
        return new HttpEntity<Greeting>(greeting);
    }

    // Get all greetings
    @RequestMapping(method = RequestMethod.GET)
    public List<HttpEntity<Greeting>> getGreetings() {
        return IntStream.range(1, 5).mapToObj(id -> new HttpEntity<Greeting>(createGreeting(id)))
                .collect(Collectors.toList());
    }

    // Hard-coded DAO for Greetings

    private Greeting createGreeting(Integer id) {

        Greeting greeting = new Greeting(id, greetingsById.get(id));

        // Add link to parent /greeting
        greeting.add(linkTo(methodOn(GreetingController.class).getGreetings()).withRel("greeting"));

        // Link to self
        greeting.add(linkTo(methodOn(GreetingController.class).getGreetingById(id)).withSelfRel());

        // Add detail links
        greeting.add(linkTo(methodOn(GreetingDetailController.class).getGreetingDetails(id)).withRel("detail"));

        return greeting;
    }

    private Map<Integer, String> greetingsById = new HashMap<>();

    @PostConstruct
    void createGreetings() {
        greetingsById.put(1, "Greetings earthlings, we shall now take over your planet");
        greetingsById.put(2, "Hello world");
        greetingsById.put(3, "Welcome Artful Dodgers!");
        greetingsById.put(4, "Hi!");
        greetingsById.put(5, "Yo!");
    }
}
