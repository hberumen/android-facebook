package hberumen.me.facebookrecipes.recipelist.di;

import javax.inject.Singleton;

import dagger.Component;
import hberumen.me.facebookrecipes.libs.di.LibsModule;
import hberumen.me.facebookrecipes.recipelist.RecipeListPresenter;
import hberumen.me.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;
import hberumen.me.facebookrecipes.recipemain.di.RecipeMainModule;

/**
 * Created by hberumen on 21/06/16.
 */
@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {

    RecipeListPresenter getPresenter();
    RecipesAdapter getAdapter();
}
