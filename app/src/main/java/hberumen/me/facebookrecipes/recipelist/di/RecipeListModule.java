package hberumen.me.facebookrecipes.recipelist.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hberumen.me.facebookrecipes.api.RecipeClient;
import hberumen.me.facebookrecipes.api.RecipeService;
import hberumen.me.facebookrecipes.entities.Recipe;
import hberumen.me.facebookrecipes.libs.base.EventBus;
import hberumen.me.facebookrecipes.libs.base.ImageLoader;
import hberumen.me.facebookrecipes.recipelist.RecipeListInteractor;
import hberumen.me.facebookrecipes.recipelist.RecipeListInteractorImpl;
import hberumen.me.facebookrecipes.recipelist.RecipeListPresenter;
import hberumen.me.facebookrecipes.recipelist.RecipeListPresenterImpl;
import hberumen.me.facebookrecipes.recipelist.RecipeListRepository;
import hberumen.me.facebookrecipes.recipelist.RecipeListRepositoryImpl;
import hberumen.me.facebookrecipes.recipelist.StoredRecipesInteractor;
import hberumen.me.facebookrecipes.recipelist.StoredRecipesInteractorImpl;
import hberumen.me.facebookrecipes.recipelist.ui.RecipeListView;
import hberumen.me.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import hberumen.me.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;
import hberumen.me.facebookrecipes.recipemain.GetNextRecipeIteractor;
import hberumen.me.facebookrecipes.recipemain.GetNextRecipeIteractorImpl;
import hberumen.me.facebookrecipes.recipemain.RecipeMainPresenter;
import hberumen.me.facebookrecipes.recipemain.RecipeMainPresenterImpl;
import hberumen.me.facebookrecipes.recipemain.RecipeMainRepository;
import hberumen.me.facebookrecipes.recipemain.RecipeMainRepositoryImpl;
import hberumen.me.facebookrecipes.recipemain.SaveRecipeInteractor;
import hberumen.me.facebookrecipes.recipemain.SaveRecipeInteractorImpl;
import hberumen.me.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by hberumen on 21/06/16.
 */
@Module
public class RecipeListModule {

    RecipeListView view;
    OnItemClickListener clickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides @Singleton
    RecipeListView providesRecipeListView(){
        return this.view;
    }

    @Provides @Singleton
    RecipeListPresenter providesRecipeListPresenter(EventBus eventBus, RecipeListView view, RecipeListInteractor listInteractor, StoredRecipesInteractor storedInteractor){
        return new RecipeListPresenterImpl(eventBus,view,listInteractor, storedInteractor);
    }

    @Provides @Singleton
    StoredRecipesInteractor providesStoredRecipesInteractor(RecipeListRepository repository){
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repository){
        return new RecipeListInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeListRepository providesRecipeListRepository(EventBus eventBus){
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides @Singleton
    RecipesAdapter providesRecipesAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new RecipesAdapter(recipeList, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides @Singleton
    List<Recipe> providesEmptyList(){
        return new ArrayList<Recipe>();
    }
}
