package com.example.openaiapidemo.service;

import com.example.openaiapidemo.model.Answer;
import com.example.openaiapidemo.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class RecipeGeneratorService implements RecipeGenerator {

    private final ChatClient chatClient;

    private final String recipeTempalte = """
            Answer for {foodname} for {question}?""";

    public RecipeGeneratorService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public Answer generateRecipe(Question question) {
        return new Answer(getMessage(question).getResult().getOutput().getContent());
    }

    public ChatResponse getMessage(Question question) {
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(recipeTempalte)
                        .param("foodname", question.getFoodName())
                        .param("question", question.getQuestion())
                )
                .call()
                .chatResponse();
    }
}
