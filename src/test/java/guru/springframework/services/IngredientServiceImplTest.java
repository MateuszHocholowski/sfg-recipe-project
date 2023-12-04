package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
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
}