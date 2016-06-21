package hberumen.me.facebookrecipes.recipemain.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hberumen.me.facebookrecipes.api.RecipeClient;
import hberumen.me.facebookrecipes.api.RecipeService;
import hberumen.me.facebookrecipes.libs.base.EventBus;
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
public class RecipeMainModule {

    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides @Singleton
    RecipeMainView providesRecipeMainView(){
        return this.view;
    }

    @Provides @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeIteractor getNextRecipeIteractor){
        return new RecipeMainPresenterImpl(eventBus,view,saveRecipeInteractor, getNextRecipeIteractor);
    }

    @Provides @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository){
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides @Singleton
    GetNextRecipeIteractor providesGetNextRecipeIteractor(RecipeMainRepository repository){
        return new GetNextRecipeIteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeMainRepository providesRecipeMainRepository(EventBus eventBus, RecipeService service){
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }

}
