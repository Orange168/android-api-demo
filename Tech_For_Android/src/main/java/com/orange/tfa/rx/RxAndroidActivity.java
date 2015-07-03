package com.orange.tfa.rx;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by Edward.Lin on 2015/7/2 17:29
 */
public class RxAndroidActivity extends Activity {


    private static final String VOD_URL = "http://gvod.togic.com";

    private HashMap<String,String> queryMap = null ;
    interface VODObservalbe {
        @GET("/api/programs/{category}")
        Observable<VideoInfo> videoInfoObservable(@Path("category") String category, @QueryMap Map<String, String> map) ;
    }

    interface VODList{
        @GET("/api/programs/{category}")
        List<VideoInfo> videoInfoList(@Path("category") String category, @QueryMap Map<String, String> map) ;
    }

    static class VideoInfo {
        String title;
        String poster;
        String category_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView imageView = new ImageView(this) ;
        LinearLayout layout = new LinearLayout(this) ;
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT) ;
        imageView.setLayoutParams(lp);
        layout.addView(imageView);
        setContentView(layout);
        initQueryMap() ;

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(VOD_URL).build() ;

	    VODObservalbe mVODObServices =  restAdapter.create(VODObservalbe.class);



//        for (VideoInfo v : list) {
//            System.out.println("[" + v.category_id + "]" + v.title + " (" + v.poster + ")");
//        }

    }

    private void initQueryMap() {
        queryMap = new HashMap<>() ;
        queryMap.put("package", "com_togic_livevideo") ;
        queryMap.put("deviceId", "3afd570e-4ab0-7865-bf11-fcf0ff802abf") ;
        queryMap.put("deviceId", "55") ;
        queryMap.put("versionName", "2.8.3") ;
        queryMap.put("model", "X801") ;
        queryMap.put("distributor", "vendor") ;
        queryMap.put("resolution", "720") ;
        queryMap.put("orderBy", "0") ;
        queryMap.put("pageSize", "6") ;
        queryMap.put("pageNo", "1") ;
        queryMap.put("u_token", "") ;
        queryMap.put("cess_token", "31f004a69b8e741f40fe5cc394e4f2136202aee4a") ;
    }

}
