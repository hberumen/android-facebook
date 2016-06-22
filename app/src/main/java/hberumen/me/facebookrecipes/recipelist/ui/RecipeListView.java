package hberumen.me.facebookrecipes.recipelist.ui;

import java.util.List;

import hberumen.me.facebookrecipes.entities.Recipe;

/**
 * Created by hberumen on 21/06/16.
 */
public interface RecipeListView {
    void setRecipes(List<Recipe> data);
    void recipeUpdated();
    void recipeDelete(Recipe recipe);
}
