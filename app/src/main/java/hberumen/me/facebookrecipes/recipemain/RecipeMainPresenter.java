package hberumen.me.facebookrecipes.recipemain;

import hberumen.me.facebookrecipes.entities.Recipe;
import hberumen.me.facebookrecipes.recipemain.events.RecipeMainEvent;
import hberumen.me.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by hberumen on 21/06/16.
 */
public interface RecipeMainPresenter {
    void onCreate();
    void onDestroy();

    void dismisssRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void setEventMainThread(RecipeMainEvent recipeMainEvent);

    void imageError(String error);
    void imageReady();
    RecipeMainView getView();

}
