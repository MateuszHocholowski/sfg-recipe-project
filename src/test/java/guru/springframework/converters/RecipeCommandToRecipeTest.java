package guru.springframework.converters;

import guru.springframework.commands.*;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    private final IngredientCommandToIngredient ingredientConverter = new IngredientCommandToIngredient(uomConverter);
    private final NotesCommandToNotes notesConverter = new NotesCommandToNotes();
    private final CategoryCommandToCategory categoryConverter = new CategoryCommandToCategory();
    private final RecipeCommandToRecipe converter =
            new RecipeCommandToRecipe(ingredientConverter,notesConverter,categoryConverter);
    private NotesCommand notes;
    private CategoryCommand category;
    private IngredientCommand ingredient;
    private UnitOfMeasureCommand uom;
    private Set<CategoryCommand> categories;
    private Set<IngredientCommand> ingredients;
    private final long CATEGORY_ID = 10L;
    private final String CATEGORY_DESC = "testDescription";
    private final long NOTES_ID = 11L;
    private final String NOTES_RN = "testRecipeNotes";
    private final long UOM_ID = 4L;
    private final String UOM_DESC = "testUom";
    private final long ING_ID = 6L;
    private final BigDecimal ING_AMOUNT = new BigDecimal("15");
    private final String ING_DESC = "testIngDescription";

    @Before
    public void setUp() throws Exception {
        category = new CategoryCommand();
        category.setId(CATEGORY_ID);
        category.setDescription(CATEGORY_DESC);

        notes = new NotesCommand();
        notes.setRecipeNotes(NOTES_RN);
        notes.setId(NOTES_ID);

        categories = new HashSet<>();
        categories.add(category);

        uom = new UnitOfMeasureCommand();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESC);

        ingredient = new IngredientCommand();
        ingredient.setId(ING_ID);
        ingredient.setAmount(ING_AMOUNT);
        ingredient.setDescription(ING_DESC);
        ingredient.setUnitOfMeasure(uom);

        ingredients = new HashSet<>();
        ingredients.add(ingredient);
    }

    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setCategories(categories);
        recipeCommand.setId(1L);
        recipeCommand.setDescription("testDescription");
        recipeCommand.setDirections("testDirections");
        recipeCommand.setDifficulty(Difficulty.EASY);
        recipeCommand.setIngredients(ingredients);
        recipeCommand.setImage(new Byte[1]);
        recipeCommand.setServing(1);
        recipeCommand.setNotes(notes);
        recipeCommand.setUrl("testUrl");
        recipeCommand.setCookTime(5);
        recipeCommand.setPrepTime(7);
        recipeCommand.setSource("testSource");
        //when
        Recipe recipe = converter.convert(recipeCommand);
        //then
        assertNotNull(recipe);
        assertEquals(recipe.getCategories().size(),recipeCommand.getCategories().size());
        assertEquals(recipe.getCategories().iterator().next().getId(),
                recipeCommand.getCategories().iterator().next().getId());
        assertEquals(recipe.getId(),recipeCommand.getId());
        assertEquals(recipe.getDirections(),recipeCommand.getDirections());
        assertEquals(recipe.getDifficulty(),recipeCommand.getDifficulty());
        assertEquals(recipe.getImage(),recipeCommand.getImage());
        assertEquals(recipe.getCookTime(),recipeCommand.getCookTime());
        assertEquals(recipe.getPrepTime(),recipeCommand.getPrepTime());
        assertEquals(recipe.getServing(),recipeCommand.getServing());
        assertEquals(recipe.getNotes().getId(),recipeCommand.getNotes().getId());
        assertEquals(recipe.getNotes().getRecipeNotes(),recipeCommand.getNotes().getRecipeNotes());
        assertEquals(recipe.getUrl(),recipeCommand.getUrl());
        assertEquals(recipe.getServing(),recipeCommand.getServing());
        assertEquals(recipe.getSource(),recipeCommand.getSource());
        assertEquals(recipe.getIngredients().size(),recipeCommand.getIngredients().size());
    }
}