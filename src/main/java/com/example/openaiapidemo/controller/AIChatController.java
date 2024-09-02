package com.example.openaiapidemo.controller;

import com.example.openaiapidemo.service.MovieService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIChatController {

    @Autowired
    private MovieService movieService;

    private final ChatClient chatClient;

    public AIChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(value="message", defaultValue="Give me java hello world program ?") String message) {
        ChatResponse chatResponse = getMessage(message);
        return chatResponse.getResult().getOutput().getContent();
    }


    public ChatResponse getMessage(String message) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        return chatResponse;
    }

    @GetMapping("/chatjson")
    public ChatResponse chatJson(@RequestParam(value="message", defaultValue="Please give me a simple joke in one line ") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
    }

    @GetMapping("/metadata")
    public ChatResponse metadata(@RequestParam(value="message", defaultValue="Please give me a simple joke in one line ") String message) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
        System.out.println("get model " + chatResponse.getMetadata().getModel());
        return chatResponse;
    }

    @GetMapping("/movieinfo")
    public String movieInfo(@RequestParam(value="movieName", defaultValue="The Matrix") String movieName) {
        return movieService.movieInfo(movieName);
    }
}
