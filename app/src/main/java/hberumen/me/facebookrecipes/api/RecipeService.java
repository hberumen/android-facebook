package hberumen.me.facebookrecipes.api;

import hberumen.me.facebookrecipes.entities.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hberumen on 20/06/16.
 */
public interface RecipeService {
    @GET("search")
    Call<RecipeSearchResponse> search(@Query("key") String key,
                                      @Query("sort") String sort,
                                      @Query("count") int count,
                                      @Query("page") int page);
}
