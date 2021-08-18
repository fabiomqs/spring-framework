package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;


class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {

        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        Assertions.assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}