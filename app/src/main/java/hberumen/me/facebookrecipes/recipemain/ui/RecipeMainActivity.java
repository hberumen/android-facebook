package hberumen.me.facebookrecipes.recipemain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hberumen.me.facebookrecipes.FacebookRecipesApp;
import hberumen.me.facebookrecipes.R;
import hberumen.me.facebookrecipes.recipelist.ui.RecipeListActivity;
import hberumen.me.facebookrecipes.entities.Recipe;
import hberumen.me.facebookrecipes.libs.base.ImageLoader;
import hberumen.me.facebookrecipes.recipemain.RecipeMainPresenter;
import hberumen.me.facebookrecipes.recipemain.di.RecipeMainComponent;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView, SwipeGestureListener {

    @BindView(R.id.imgRecipe)
    ImageView imgRecipe;
    @BindView(R.id.imgDissmiss)
    ImageButton imgDissmiss;
    @BindView(R.id.imgKeep)
    ImageButton imgKeep;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutContainer)
    RelativeLayout layoutContainer;

    private RecipeMainPresenter presenter;
    private Recipe currentRecipe;
    private ImageLoader imageLoader;
    private RecipeMainComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);

        setUpInjection();
        setUpImageLoader();
        setUpGestureDetection();
        presenter.onCreate();
        presenter.getNextRecipe();
    }

    private void setUpGestureDetection() {
        final GestureDetector gestureDetector = new GestureDetector(
                this, new SwipeGestureDetector(this));
        View.OnTouchListener gestureOntouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        imgRecipe.setOnTouchListener(gestureOntouchListener);

    }

    private void setUpImageLoader() {
        RequestListener glideListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoadingListener(glideListener);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_list){
            navigateToListScreen();
        }else if(id == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FacebookRecipesApp app = (FacebookRecipesApp)getApplication();
        app.logout();
    }

    private void navigateToListScreen() {
        startActivity(new Intent(this, RecipeListActivity.class));
    }

    private void setUpInjection() {
        FacebookRecipesApp app = (FacebookRecipesApp)getApplication();
        component = app.getRecipeMainComponent(this, this);
        imageLoader = getImageLoader();
        presenter = getPresenter();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPUIElements() {
        imgKeep.setVisibility(View.VISIBLE);
        imgDissmiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void hiddenUIElements() {
        imgKeep.setVisibility(View.GONE);
        imgDissmiss.setVisibility(View.GONE);
    }

    @Override
    public void saveAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.save_animation);
        animation.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(animation);
    }

    @Override
    public void dismissAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.dismiss_animation);
        animation.setAnimationListener(getAnimationListener());
        imgRecipe.startAnimation(animation);
    }

    private Animation.AnimationListener getAnimationListener(){
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

    private void clearImage(){
        imgRecipe.setImageResource(0);
    }

    @OnClick(R.id.imgKeep)
    @Override
    public void onKeep(){
        if(currentRecipe != null){
            presenter.saveRecipe(currentRecipe);
        }
    }

    @OnClick(R.id.imgDissmiss)
    @Override
    public void onDismiss(){
        presenter.dismisssRecipe();
    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(layoutContainer, R.string.recipemain_notice_saved, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        this.currentRecipe = recipe;
        imageLoader.load(imgRecipe, recipe.getImgURL());
    }

    @Override
    public void onGetRecipeError(String error) {
        String msgError = String.format(getString(R.string.recipemain_error), error);
        Snackbar.make(layoutContainer, msgError, Snackbar.LENGTH_SHORT).show();
    }

    public ImageLoader getImageLoader() {
        return component.getImageLoader();
    }

    public RecipeMainPresenter getPresenter() {
        return component.getPresenter();
    }
}
