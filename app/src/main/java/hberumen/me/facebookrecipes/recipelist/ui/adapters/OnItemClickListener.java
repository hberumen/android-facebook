package hberumen.me.facebookrecipes.recipelist.ui.adapters;

import hberumen.me.facebookrecipes.entities.Recipe;

/**
 * Created by hberumen on 21/06/16.
 */
public interface OnItemClickListener {
    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeteleClick(Recipe recipe);
}
