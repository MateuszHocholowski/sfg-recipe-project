package guru.springframework.controllers;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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
    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate (@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        log.debug("Saved recipe. Id: " + command.getRecipeId());
        log.debug("saved ingredient. Id: " + command.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newRecipeIngredient(@PathVariable String recipeId, Model model) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.parseLong(recipeId));

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.parseLong(recipeId));
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("ingredient",ingredientCommand);
        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientform";
    }
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.parseLong(recipeId),Long.parseLong(id)));
        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientform";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteRecipe(@PathVariable String recipeId, @PathVariable String id) {
        log.debug("deleting ingredient id: " + id + "from recipe id: " + recipeId);
        ingredientService.deleteIngredientById(Long.parseLong(recipeId),Long.parseLong(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
