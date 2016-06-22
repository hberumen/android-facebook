package hberumen.me.facebookrecipes.recipelist;

import hberumen.me.facebookrecipes.entities.Recipe;
import hberumen.me.facebookrecipes.recipelist.events.RecipeListEvent;
import hberumen.me.facebookrecipes.recipelist.ui.RecipeListView;

/**
 * Created by hberumen on 21/06/16.
 */
public interface RecipeListPresenter {
    void onCreate();
    void onDestroy();

    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);

    RecipeListView getView();
}
