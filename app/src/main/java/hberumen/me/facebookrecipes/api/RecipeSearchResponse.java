package hberumen.me.facebookrecipes.api;

import java.util.List;

import hberumen.me.facebookrecipes.entities.Recipe;

/**
 * Created by hberumen on 20/06/16.
 */
public class RecipeSearchResponse {

    private int count;
    private List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe getFirstRecipe(){
        Recipe first = null;
        if(!recipes.isEmpty()){
            first = recipes.get(0);
        }
        return first;
    }
}
