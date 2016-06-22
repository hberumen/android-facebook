package hberumen.me.facebookrecipes.recipelist;

/**
 * Created by hberumen on 21/06/16.
 */
public class RecipeListInteractorImpl implements RecipeListInteractor {

    private RecipeListRepository repository;

    public RecipeListInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedRecipes();
    }
}
