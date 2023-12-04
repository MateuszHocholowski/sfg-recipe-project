package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;
    @Mock
    private RecipeRepository recipeRepository;

//    @BeforeEach
//    void setUp() throws Exception {
////        MockitoAnnotations.initMocks(this);
//
////        recipeService = new RecipeServiceImpl(recipeRepository);
//    }

    @Test
    public void getRecipesTest() {

        Recipe recipe = new Recipe();
        List<Recipe> recipesData = new ArrayList<>(List.of(recipe));

        when(recipeRepository.findAll()).thenReturn(recipesData);
        List<Recipe> recipes = recipeService.getRecipes();
        verify(recipeRepository, times(1)).findAll();

        assertEquals(recipes.size(),1);
    }

    @Test
    public void getRecipeByIdTest() {
        Recipe recipe= new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned);
        assertEquals(1L,recipeReturned.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }
    @Test
    public void testDeleteRecipeById() throws Exception {
        Long idToDelete = 2L;
        recipeService.deleteById(idToDelete);

        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}