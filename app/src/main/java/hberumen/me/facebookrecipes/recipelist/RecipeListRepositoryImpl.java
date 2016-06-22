package hberumen.me.facebookrecipes.recipelist;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.Arrays;
import java.util.List;

import hberumen.me.facebookrecipes.entities.Recipe;
import hberumen.me.facebookrecipes.libs.base.EventBus;
import hberumen.me.facebookrecipes.recipelist.events.RecipeListEvent;

/**
 * Created by hberumen on 21/06/16.
 */
public class RecipeListRepositoryImpl implements RecipeListRepository {

    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList storedRecipes = new FlowCursorList(false, Recipe.class);
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    private void post(int type, List<Recipe> recipes){
        RecipeListEvent event = new RecipeListEvent();
        event.setType(type);
        event.setRecipeList(recipes);
        eventBus.post(event);
    }

    private void post(){
        post(RecipeListEvent.UPDATE_EVENT, null);
    }
}
