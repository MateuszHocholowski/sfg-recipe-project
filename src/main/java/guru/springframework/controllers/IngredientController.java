package guru.springframework.controllers;

import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String getIngredientsList(@PathVariable String recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id " + recipeId);
        model.addAttribute("recipe",recipeService.findCommandById(Long.parseLong(recipeId)));

        return "recipe/ingredients/list";
    }
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId),Long.parseLong(id)));
        return "recipe/ingredients/show";
    }
}
