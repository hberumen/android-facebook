package hberumen.me.facebookrecipes;

import android.app.Application;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;

import hberumen.me.facebookrecipes.libs.di.LibsModule;
import hberumen.me.facebookrecipes.login.ui.LoginActivity;
import hberumen.me.facebookrecipes.recipelist.di.DaggerRecipeListComponent;
import hberumen.me.facebookrecipes.recipelist.di.RecipeListComponent;
import hberumen.me.facebookrecipes.recipelist.di.RecipeListModule;
import hberumen.me.facebookrecipes.recipelist.ui.RecipeListActivity;
import hberumen.me.facebookrecipes.recipelist.ui.RecipeListView;
import hberumen.me.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import hberumen.me.facebookrecipes.recipemain.di.DaggerRecipeMainComponent;
import hberumen.me.facebookrecipes.recipemain.di.RecipeMainComponent;
import hberumen.me.facebookrecipes.recipemain.di.RecipeMainModule;
import hberumen.me.facebookrecipes.recipemain.ui.RecipeMainActivity;
import hberumen.me.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by hberumen on 20/06/16.
 */
public class FacebookRecipesApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view){
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }

    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener clickListener){
        return DaggerRecipeListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(view, clickListener))
                .build();
    }
}
