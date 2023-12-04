package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import guru.springframework.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;
    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/","","/index","/index.html"})
    public String getRecipes(Model model) {
        log.debug("Getting Index page");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
