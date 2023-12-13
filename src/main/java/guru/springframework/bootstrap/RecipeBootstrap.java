package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository,
                           UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading Bootstrap Data");
        recipeRepository.saveAll(getRecipes());
    }
    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(tableSpoonUomOptional.isEmpty()){
            throw new RuntimeException("Tablespoon UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(teaSpoonUomOptional.isEmpty()){
            throw new RuntimeException("Teaspoon UOM Not Found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if(pinchUomOptional.isEmpty()){
            throw new RuntimeException("Pinch UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(cupsUomOptional.isEmpty()){
            throw new RuntimeException("Cup UOM Not Found");
        }
        Optional<UnitOfMeasure> emptyUomOptional = unitOfMeasureRepository.findByDescription("");
        if (emptyUomOptional.isEmpty()){
            throw new RuntimeException("Empty UOM Not Found");
        }

        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure emptyUom = emptyUomOptional.get();

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(mexicanCategoryOptional.isEmpty()){
            throw new RuntimeException("Expected Category Not Found");
        }
        if(americanCategoryOptional.isEmpty()){
            throw new RuntimeException("Expected Category Not Found");
        }
        Category mexicanCategory = mexicanCategoryOptional.get();
        Category americanCategory = americanCategoryOptional.get();
        log.debug("loading Guacamole recipe...");
        Recipe guacamole = new Recipe();
        guacamole.getCategories().add(mexicanCategory);
        guacamole.getCategories().add(americanCategory);
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setCookTime(10);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setPrepTime(10);
        guacamole.setServing(4);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("1.  Cut the avocados:\n" +
                "\n" +
                "Cut the avocados in half. Remove the pit. " +
                "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "(See How to Cut and Peel an Avocado.) Place in a bowl. " +
                "\n" +
                " 2. Mash the avocado flesh:\n" +
                "\n" +
                "Using a fork, roughly mash the avocado. " +
                "(Don't overdo it! The guacamole should be a little chunky.) " +
                "\n" +
                " 3.Add the remaining ingredients to taste:" +
                "\n" +
                "Sprinkle with salt and lime (or lemon) juice. " +
                "The acid in the lime juice will provide some balance to the richness of the avocado " +
                "and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. " +
                "Chili peppers vary individually in their spiciness. " +
                "So, start with a half of one chili pepper and add more to the guacamole " +
                "to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in " +
                "the fresh ingredients. Start with this recipe and adjust to your taste. ");

        Notes note = new Notes();
        note.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. " +
                "If no gloves are available, wash your hands thoroughly after handling, " +
                "and do not touch your eyes or the area near your eyes for several hours afterwards.");
        guacamole.setNotes(note);

        Ingredient avocado = new Ingredient();
        avocado.setDescription("ripe avocados");
        avocado.setAmount(new BigDecimal("2"));
        avocado.setUnitOfMeasure(emptyUom);
        guacamole.addIngredient(avocado);

        Ingredient salt = new Ingredient();
        salt.setDescription("kosher salt, plus more to taste");
        salt.setAmount(new BigDecimal("0.25"));
        salt.setUnitOfMeasure(teaSpoonUom);
        guacamole.addIngredient(salt);

        Ingredient lime = new Ingredient();
        lime.setDescription("of fresh lime or lemon juice");
        lime.setAmount(new BigDecimal("1"));
        lime.setUnitOfMeasure(tableSpoonUom);
        guacamole.addIngredient(lime);

        Ingredient onion = new Ingredient();
        onion.setAmount(new BigDecimal("3"));
        onion.setUnitOfMeasure(tableSpoonUom);
        onion.setDescription("s minced red onion or thinly sliced green onion");
        guacamole.addIngredient(onion);

        Ingredient pepper = new Ingredient();
        pepper.setAmount(new BigDecimal("1"));
        pepper.setDescription("to 2 serrano (or jalapeno) chillis, stems and seeds removed, minced");
        pepper.setUnitOfMeasure(emptyUom);
        guacamole.addIngredient(pepper);

        Ingredient cilantro = new Ingredient();
        cilantro.setAmount(new BigDecimal("2"));
        cilantro.setUnitOfMeasure(tableSpoonUom);
        cilantro.setDescription("s cilantro (leaves and tender stems), finely chopped");
        guacamole.addIngredient(cilantro);
//
        Ingredient blackPepper = new Ingredient();
        blackPepper.setUnitOfMeasure(pinchUom);
        blackPepper.setDescription("freshly ground black pepper");
        guacamole.addIngredient(blackPepper);

        Ingredient tomato = new Ingredient();
        tomato.setAmount(new BigDecimal("0.5"));
        tomato.setDescription("ripe tomato, chopped (optional)");
        tomato.setUnitOfMeasure(emptyUom);
        guacamole.addIngredient(tomato);

        Ingredient radish = new Ingredient();
        radish.setDescription("Red radish or jicama slices for garnish (optional)");
        radish.setUnitOfMeasure(emptyUom);
        guacamole.addIngredient(radish);


        Ingredient tortilla = new Ingredient();
        tortilla.setDescription("Tortilla chips, to serve");
        tortilla.setUnitOfMeasure(emptyUom);
        guacamole.addIngredient(tortilla);

        recipes.add(guacamole);
        log.debug("Guacamole recipe loaded");
        return recipes;
    }

}
