package hberumen.me.facebookrecipes.recipemain;

import java.util.Random;

/**
 * Created by hberumen on 21/06/16.
 */
public class GetNextRecipeIteractorImpl implements GetNextRecipeIteractor {
    RecipeMainRepository repository;

    public GetNextRecipeIteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute() {
        int recipe = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipe);
        repository.getNextRecipe();
    }
}
