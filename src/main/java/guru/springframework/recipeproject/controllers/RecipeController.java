package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeServiceImpl recipeServiceImpl;

    @Autowired
    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }

    @RequestMapping({"/recipe","/recipes"})
    public String getRecipes(Model model) {
        log.debug("Loading recipes...");
        model.addAttribute("recipes", recipeServiceImpl.getRecipes());
        log.debug("Recipes loaded");
        return "recipe/index";
    }
}
