package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter,
                                 CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        } else {
            Recipe recipe = new Recipe();
            recipe.setId(source.getId());
            recipe.setUrl(source.getUrl());
            recipe.setSource(source.getSource());
            recipe.setServing(source.getServing());
            Set<Ingredient> ingredients = source.getIngredients().stream()
                    .map(ingredientConverter::convert)
                    .collect(Collectors.toSet());
            recipe.setIngredients(ingredients);
            recipe.setNotes(notesConverter.convert(source.getNotes()));
            recipe.setPrepTime(source.getPrepTime());
            recipe.setDescription(source.getDescription());
            recipe.setDirections(source.getDirections());
            recipe.setImage(source.getImage());
            recipe.setDifficulty(source.getDifficulty());
            recipe.setCookTime(source.getCookTime());
            Set<Category> categories = source.getCategories().stream()
                    .map(categoryConverter::convert)
                    .collect(Collectors.toSet());
            recipe.setCategories(categories);
            return recipe;
        }
    }
}
