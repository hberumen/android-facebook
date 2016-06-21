package hberumen.me.facebookrecipes.recipemain.ui;

import hberumen.me.facebookrecipes.entities.Recipe;

/**
 * Created by hberumen on 21/06/16.
 */
public interface RecipeMainView {
    void showProgress();
    void hiddenProgress();
    void showPUIElements();
    void hiddenUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);
    void onGetRecipeError(String error);

}
