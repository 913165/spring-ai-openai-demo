package com.example.openaiapidemo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final ChatClient chatClient;

    @Value("classpath:prompts/movie-template.st")
    private Resource movieResource;

    public MovieService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String movieInfo(String movieName) {
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(movieResource)
                        .param("moviename", movieName)
                )
                .call()
                .chatResponse().getResult().getOutput().getContent();
    }
}
