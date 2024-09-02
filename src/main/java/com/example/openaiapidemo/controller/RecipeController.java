package com.example.openaiapidemo.controller;

import com.example.openaiapidemo.model.Answer;
import com.example.openaiapidemo.model.Question;
import com.example.openaiapidemo.service.RecipeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
    @Autowired
    private RecipeGenerator recipeGeneratorService;

    @GetMapping("/generate-recipe")
    public Answer generateRecipe(@RequestParam(value="question", defaultValue="What are the ingredients")String question,
                                 @RequestParam(value="foodName", defaultValue="pizza")  String foodName) {
        return recipeGeneratorService.generateRecipe(new Question(question,foodName));
    }
}

