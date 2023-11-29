package guru.springframework.recipeproject.repositories;

import guru.springframework.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Override
    List<Recipe> findAll();
}
