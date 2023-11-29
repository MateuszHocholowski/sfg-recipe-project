package guru.springframework.recipeproject.controllers;

import guru.springframework.recipeproject.services.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeServiceImpl recipeServiceImpl;

    @Autowired
    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }

    @RequestMapping({"/recipe","/recipes"})
    public String getRecipes(Model model) {
        model.addAttribute("recipes", recipeServiceImpl.getRecipes());
        return "recipe/index";
    }
}
