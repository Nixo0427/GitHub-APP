package github.nixo.com.utils.glide;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class CustomAppGlideModule extends AppGlideModule {

    public void appOptions(Context context , GlideBuilder builder){
        builder.setMemoryCache(new LruResourceCache(20 * 1024 * 1024));
    }

}
