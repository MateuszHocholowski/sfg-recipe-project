package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    private final IngredientToIngredientCommand ingredientConverter = new IngredientToIngredientCommand(uomConverter);
    private final NotesToNotesCommand notesConverter = new NotesToNotesCommand();
    private final CategoryToCategoryCommand categoryConverter = new CategoryToCategoryCommand();
    private final RecipeToRecipeCommand converter =
            new RecipeToRecipeCommand(ingredientConverter,notesConverter,categoryConverter);
    private final long CAT_ID = 10L;
    private final String CAT_DESC = "testCatDesc";
    private final Long NOTES_ID = 11L;
    private final Long ING_ID = 6L;
    private final Long REC_ID = 1L;
    private final String REC_DESC = "testRecDesc";
    private final String REC_DIR = "testDir";
    private final Difficulty REC_DIF = Difficulty.EASY;
    private final Byte[] REC_IMG = new Byte[1];
    private final int REC_SERV = 17;
    private final String REC_URL = "testRecUrl";
    private final String REC_SRC = "testRecSource";
    private final int REC_CKT = 7;
    private final int REC_PRT = 12;
    private Set<Ingredient> ingredients;
    private Set<Category> categories;
    private Notes notes;


    @Before
    public void setUp() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ING_ID);

        ingredients = new HashSet<>();
        ingredients.add(ingredient);

        notes = new Notes();
        notes.setId(NOTES_ID);

        Category category = new Category();
        category.setId(CAT_ID);
        category.setDescription(CAT_DESC);

        categories = new HashSet<>();
        categories.add(category);
    }

    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setDescription(REC_DESC);
        recipe.setCategories(categories);
        recipe.setId(REC_ID);
        recipe.setDirections(REC_DIR);
        recipe.setDifficulty(REC_DIF);
        recipe.setImage(REC_IMG);
        recipe.setIngredients(ingredients);
        recipe.setServing(REC_SERV);
        recipe.setUrl(REC_URL);
        recipe.setSource(REC_SRC);
        recipe.setCookTime(REC_CKT);
        recipe.setPrepTime(REC_PRT);
        recipe.setNotes(notes);
        //when
        RecipeCommand recipeCommand = converter.convert(recipe);
        //then
        assertNotNull(recipeCommand);
        assertEquals(REC_DESC, recipeCommand.getDescription());
        assertEquals(REC_IMG, recipeCommand.getImage());
        assertEquals(REC_CKT, recipeCommand.getCookTime());
        assertEquals(REC_DIR, recipeCommand.getDirections());
        assertEquals(REC_ID, recipeCommand.getId());
        assertEquals(REC_PRT, recipeCommand.getPrepTime());
        assertEquals(REC_URL, recipeCommand.getUrl());
        assertEquals(REC_DIF, recipeCommand.getDifficulty());
        assertEquals(REC_SERV, recipeCommand.getServing());
        assertEquals(REC_SRC, recipeCommand.getSource());

        assertEquals(1,recipeCommand.getIngredients().size());
        assertEquals(ING_ID,recipeCommand.getIngredients().iterator().next().getId());

        assertEquals(1,recipeCommand.getCategories().size());
        assertEquals(CAT_DESC,recipeCommand.getCategories().iterator().next().getDescription());

        assertEquals(NOTES_ID,recipeCommand.getNotes().getId());
    }
}