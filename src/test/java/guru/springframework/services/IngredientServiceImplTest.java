package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private  IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Test
    public void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(9L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(4L);
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(4L);
        ingredientCommand.setRecipeId(3L);
        recipe.setIngredients(Set.of(ingredient));
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
        when(ingredientToIngredientCommand.convert(any())).thenReturn(ingredientCommand);
        //when
        IngredientCommand returnedIngredientCommand = ingredientService.findByRecipeIdAndIngredientId(9L,4L);

        //then
        assertNotNull(returnedIngredientCommand);
        assertEquals(Long.valueOf(4),returnedIngredientCommand.getId());
        assertEquals(Long.valueOf(3),returnedIngredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveRecipeCommand() throws Exception {
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(5L);
        command.setUnitOfMeasure(uomCommand);
        UnitOfMeasure uom = new UnitOfMeasure();

        Recipe recipe = new Recipe();
        recipe.setId(2L);
        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId(3L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(2L);
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);
        when(ingredientToIngredientCommand.convert(any())).thenReturn(command);
        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(uom));

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        assertEquals(3L,(long) savedCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any());
    }

    @Test
    public void testDeleteIngredientById() {
        Recipe recipe = new Recipe();
        recipe.setId(5L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(9L);
        ingredient.setRecipe(recipe);
        recipe.addIngredient(ingredient);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ingredientService.deleteIngredientById(5L,9L);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any());
        assertEquals(0,recipe.getIngredients().size());
    }
}