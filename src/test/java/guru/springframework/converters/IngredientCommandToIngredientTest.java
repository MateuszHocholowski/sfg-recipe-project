package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    private final IngredientCommandToIngredient converter = new IngredientCommandToIngredient(uomConverter);
    private UnitOfMeasureCommand uomCommand;

    @Before
    public void setUp() throws Exception {
        uomCommand = new UnitOfMeasureCommand();
        uomCommand.setDescription("uomTest");
        uomCommand.setId(2L);
    }
    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setDescription("test");
        ingredientCommand.setAmount(new BigDecimal("1"));
        ingredientCommand.setUnitOfMeasure(uomCommand);
        //when
        Ingredient ingredient = converter.convert(ingredientCommand);
        //then
        assertNotNull(ingredient);
        assertEquals(ingredient.getId(),ingredientCommand.getId());
        assertEquals(ingredient.getDescription(),ingredientCommand.getDescription());
        assertEquals(ingredient.getAmount(),ingredientCommand.getAmount());
        assertEquals(ingredient.getUnitOfMeasure().getDescription(),
                ingredientCommand.getUnitOfMeasure().getDescription());
        assertEquals(ingredient.getUnitOfMeasure().getId(),
                ingredientCommand.getUnitOfMeasure().getId());
    }
}