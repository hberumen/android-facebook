package hberumen.me.facebookrecipes.libs.base;

import android.widget.ImageView;

/**
 * Created by hberumen on 14/06/16.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
