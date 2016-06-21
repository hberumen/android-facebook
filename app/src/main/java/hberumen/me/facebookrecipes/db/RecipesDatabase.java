package hberumen.me.facebookrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by hberumen on 20/06/16.
 */

@Database(name = RecipesDatabase.NAME, version = RecipesDatabase.VERSION)
public class RecipesDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "Recipes";

}
