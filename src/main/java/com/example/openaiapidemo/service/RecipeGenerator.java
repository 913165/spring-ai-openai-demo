package com.example.openaiapidemo.service;

import com.example.openaiapidemo.model.Answer;
import com.example.openaiapidemo.model.Question;

public interface RecipeGenerator {
    public Answer generateRecipe(Question question);
}
