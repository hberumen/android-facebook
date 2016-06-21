package hberumen.me.facebookrecipes.recipemain.di;

import javax.inject.Singleton;

import dagger.Component;
import hberumen.me.facebookrecipes.libs.base.ImageLoader;
import hberumen.me.facebookrecipes.libs.di.LibsModule;
import hberumen.me.facebookrecipes.recipemain.RecipeMainPresenter;

/**
 * Created by hberumen on 21/06/16.
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {

    //void inject(RecipeMainActivity activity);
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();
}
