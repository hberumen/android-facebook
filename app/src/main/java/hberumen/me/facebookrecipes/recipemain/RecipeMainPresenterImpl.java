package hberumen.me.facebookrecipes.recipemain;

import org.greenrobot.eventbus.Subscribe;

import hberumen.me.facebookrecipes.entities.Recipe;
import hberumen.me.facebookrecipes.libs.base.EventBus;
import hberumen.me.facebookrecipes.recipemain.events.RecipeMainEvent;
import hberumen.me.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by hberumen on 21/06/16.
 */
public class RecipeMainPresenterImpl implements RecipeMainPresenter {

    private EventBus eventBus;
    private RecipeMainView view;
    private SaveRecipeInteractor saveRecipeInteractor;
    private GetNextRecipeIteractor getNextRecipeIteractor;


    public RecipeMainPresenterImpl(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveRecipeInteractor, GetNextRecipeIteractor getNextRecipeIteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveRecipeInteractor = saveRecipeInteractor;
        this.getNextRecipeIteractor = getNextRecipeIteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;

    }

    @Override
    public void dismisssRecipe() {
        if(view != null){
            view.dismissAnimation();
        }
        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if(view != null){
            view.hiddenUIElements();
            view.showProgress();
        }
        getNextRecipeIteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if(view != null){
            view.saveAnimation();
            view.hiddenUIElements();
            view.showProgress();
        }
        saveRecipeInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void setEventMainThread(RecipeMainEvent recipeMainEvent) {
        if(view != null){
            String error = recipeMainEvent.getError();
            if(error != null){
                view.hiddenProgress();
                view.onGetRecipeError(error);
            }else{
                if(recipeMainEvent.getType() == RecipeMainEvent.NEXT_EVENT){
                    view.setRecipe(recipeMainEvent.getRecipe());
                }else if(recipeMainEvent.getType() == RecipeMainEvent.SAVE_EVENT){
                    getNextRecipeIteractor.execute();
                }
            }
        }
    }

    @Override
    public void imageError(String error) {
        if(view != null){
            view.onGetRecipeError(error);
        }
    }

    @Override
    public void imageReady() {
        if(view != null){
           view.hiddenProgress();
            view.showPUIElements();
        }
    }

    @Override
    public RecipeMainView getView() {
        return this.view;
    }
}
