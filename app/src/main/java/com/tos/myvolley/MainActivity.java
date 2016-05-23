package com.tos.myvolley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 *
 * Volley是Android平台网络通信库：更快，更简单，更健壮
 * Volley提供的功能：
 * 1.JSON、图片（异步）
 * 2.网络请求排序
 * 3.网络请求的优先级处理
 * 4.缓存
 * 5.多级别的取消请求
 * 6.与Activity生命周期联动
 *
 */

public class MainActivity extends AppCompatActivity {

    private ImageView miv;

    private ImageView mivv;

    private NetworkImageView miv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJSONVolley();
        init();
        loadImageVolley();
        NetWorkImageViewVolley();
    }

    private void init() {
        miv = (ImageView) findViewById(R.id.iv);
        miv1 = (NetworkImageView) findViewById(R.id.imageView1);
    }

    //获取Json字符串
    public void getJSONVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String JSONDateUrl = "http://182.92.132.128:9090/rest/json.jhtml?req={%22userId%22:%2263%22,%22methodName%22:%22QueryMsgSwitch%22}";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSONDateUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.e("response:",jsonObject+"");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("response","对不起，有问题");
                        }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    //http://img.xiami.net/images/album/img67/109067/9133360851426057172_2.jpg
    public void loadImageVolley(){
        String imageurl = "http://img.xiami.net/images/album/img67/109067/9133360851426057172_2.jpg";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }

            @Override
            public void putBitmap(String key, Bitmap value) {
                lruCache.put(key,value);
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue,imageCache);
        ImageLoader.ImageListener listener = imageLoader.getImageListener(miv,R.drawable.qwe,R.drawable.qwe);
        imageLoader.get(imageurl,listener);
    }

    public void NetWorkImageViewVolley(){
        String imageurl = "http://img.xiami.net/images/album/img67/109067/9133360851426057172_2.jpg";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }

            @Override
            public void putBitmap(String key, Bitmap value) {
                lruCache.put(key,value);
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue,imageCache);
        miv1.setTag("url");
        miv1.setImageUrl(imageurl,imageLoader);
    }

}
