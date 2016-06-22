package hberumen.me.facebookrecipes.recipelist;

import hberumen.me.facebookrecipes.entities.Recipe;

/**
 * Created by hberumen on 21/06/16.
 */
public class StoredRecipesInteractorImpl implements StoredRecipesInteractor {

    private RecipeListRepository repository;

    public StoredRecipesInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Recipe recipe) {
        repository.updateRecipe(recipe);
    }

    @Override
    public void executeDelete(Recipe recipe) {
        repository.removeRecipe(recipe);
    }
}
