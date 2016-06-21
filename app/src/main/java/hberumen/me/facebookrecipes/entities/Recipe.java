package hberumen.me.facebookrecipes.entities;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.Model;

import hberumen.me.facebookrecipes.db.RecipesDatabase;

/**
 * Created by hberumen on 20/06/16.
 */
@Table(database = RecipesDatabase.class)
public class Recipe  implements Model{
    @SerializedName("recipe_id")
    @PrimaryKey private String recipeID;

    @Column private String title;
    @SerializedName("image_url")
    @Column private String imgURL;
    @SerializedName("source_url")
    @Column private String sourceURL;
    @Column private boolean favorite;

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object obj){
        boolean equal = false;
        if(obj instanceof Recipe){
            Recipe recipe = (Recipe)obj;
            equal = this.recipeID.equals(recipe.getRecipeID());
        }
        return equal;
    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void insert() {

    }

    @Override
    public boolean exists() {
        return false;
    }
}
