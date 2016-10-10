package zhuoxin.andriody.com.newsday.Activity;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by Administrator on 9/14 0014.
 */
public class MyApplication extends Application {
    private static RequestQueue requestQueue;
    private static Gson gson;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        gson=new Gson();
        //配置信息
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480,800)
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY-1)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2*1024*1024).memoryCacheSizePercentage(12)
                .diskCacheSize(50*1024*1024).diskCacheFileCount(50)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(this))
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();
        //将配置信息交给ImageLoader对象
        ImageLoader.getInstance().init(configuration);
    }
    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }
    /**
     * 停止应用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        requestQueue.cancelAll(this);//停止所有请求
    }
}
