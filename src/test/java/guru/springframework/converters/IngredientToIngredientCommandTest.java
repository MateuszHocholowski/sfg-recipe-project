package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    private final IngredientToIngredientCommand converter = new IngredientToIngredientCommand(uomConverter);
    private Recipe recipe;
    private UnitOfMeasure unitOfMeasure;

    @Before
    public void setUp() throws Exception {
        recipe = new Recipe();
        unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription("uomDescription");
        unitOfMeasure.setId(2L);
    }

    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }
    @Test
    public void convert() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setDescription("test");
        ingredient.setAmount(new BigDecimal("1"));
        ingredient.setRecipe(recipe);
        ingredient.setUnitOfMeasure(unitOfMeasure);
        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);
        //then
        assertNotNull(ingredientCommand);
        assertEquals(ingredient.getId(),ingredientCommand.getId());
        assertEquals(ingredient.getDescription(),ingredientCommand.getDescription());
        assertEquals(ingredient.getAmount(),ingredientCommand.getAmount());
        assertEquals(ingredient.getUnitOfMeasure().getId(),ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(ingredient.getUnitOfMeasure().getDescription(),
                ingredientCommand.getUnitOfMeasure().getDescription());
    }
}