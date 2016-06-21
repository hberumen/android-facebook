package hberumen.me.facebookrecipes.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hberumen on 20/06/16.
 */
public class RecipeClient {
    private Retrofit retrofit;
    private static final String BASE_URL = "http://food2fork/api/";

    public RecipeClient() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RecipeService getRecipeService(){
        return this.retrofit.create(RecipeService.class);
    }
}
