package hberumen.me.facebookrecipes.recipelist;

import hberumen.me.facebookrecipes.entities.Recipe;

/**
 * Created by hberumen on 21/06/16.
 */
public interface StoredRecipesInteractor {
    void executeUpdate(Recipe recipe);
    void executeDelete(Recipe recipe);
}
