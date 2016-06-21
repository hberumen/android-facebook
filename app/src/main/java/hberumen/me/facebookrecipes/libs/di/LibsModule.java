package hberumen.me.facebookrecipes.libs.di;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hberumen.me.facebookrecipes.libs.GlideImageLoader;
import hberumen.me.facebookrecipes.libs.GreenRoborEventBus;
import hberumen.me.facebookrecipes.libs.base.EventBus;
import hberumen.me.facebookrecipes.libs.base.ImageLoader;

/**
 * Created by hberumen on 14/06/16.
 */
@Module
public class LibsModule {
    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    ImageLoader providerImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    Activity providesFragment(){
        return this.activity;
    }

    @Provides
    @Singleton
    EventBus providerEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRoborEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

}
