package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @InjectMocks
    private IndexController indexController;
    @Mock
    private Model model;
    @Mock
    private RecipeServiceImpl recipeService;

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {

        //given
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());
        when(recipeService.getRecipes()).thenReturn(recipes);
        var argumentCaptor = ArgumentCaptor.forClass(List.class);

        //when
        String result = indexController.getRecipes(model);

        //then
        assertEquals("index", result);
//        verify(model,times(1)).addAttribute("recipes", argumentCaptor.capture());  // need the eq before raw value
        verify(model,times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
//        verify(recipeService,times(1)).getRecipes();
        var listInController = argumentCaptor.getValue();
        assertEquals(2,listInController.size());

    }
}