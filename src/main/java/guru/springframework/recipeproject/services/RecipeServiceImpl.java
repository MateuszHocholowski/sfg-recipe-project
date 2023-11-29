package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.bootstrap.RecipeBootstrap;
//import guru.springframework.recipeproject.bootstrap.RecipeBootstrap2;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeBootstrap recipeBootstrap) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipes() {
        return new ArrayList<>(recipeRepository.findAll());
    }
}
