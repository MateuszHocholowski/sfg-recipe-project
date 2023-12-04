package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter,
                                 CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        } else {
            RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(source.getId());
            recipeCommand.setUrl(source.getUrl());
            recipeCommand.setSource(source.getSource());
            recipeCommand.setServing(source.getServing());
            Set<IngredientCommand> ingredients = source.getIngredients().stream()
                    .map(ingredientConverter::convert)
                    .collect(Collectors.toSet());
            recipeCommand.setIngredients(ingredients);
            recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
            recipeCommand.setPrepTime(source.getPrepTime());
            recipeCommand.setDescription(source.getDescription());
            recipeCommand.setDirections(source.getDirections());
            recipeCommand.setImage(source.getImage());
            recipeCommand.setDifficulty(source.getDifficulty());
            recipeCommand.setCookTime(source.getCookTime());
            Set<CategoryCommand> categories = source.getCategories().stream()
                    .map(categoryConverter::convert)
                    .collect(Collectors.toSet());
            recipeCommand.setCategories(categories);
            return recipeCommand;
        }
    }
}
