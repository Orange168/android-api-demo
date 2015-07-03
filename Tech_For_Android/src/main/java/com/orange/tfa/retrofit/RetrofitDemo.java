package com.orange.tfa.retrofit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by Edward Lin
 * on 7/1/15 8:58 PM.
 */
public class RetrofitDemo {

	private static final String requestUrl = "/api/programs/{id}?package=com_togic_livevideo&versionCode=55&deviceId=3afd570e-4ab0-7865-bf11-fcf0ff802abf&access_token=31f004a69b8e741f40fe5cc394e4f2136202aee4a&u_token=&versionName=2.8.3&model=X801&distributor=vendor&resolution=720&orderBy=0&pageSize=6&pageNo=1";

	private static final String AOD_UEL ="http://gvod.togic.com";
	private static HashMap<String,String> queryMap = null ;

	interface VOD{

		@GET(requestUrl)
		List<VideoInfo> vodSeries(@Path("id") String id);

	}
	interface VODList{
		@GET("/api/programs/{category}")
		List<VideoInfo> videoInfoList(@Path("category") String category, @QueryMap Map<String, String> map) ;
	}
	static class VideoInfo {
		String title ;
		String poster ;
		String category_id ;
	}

	public static void main(String... args){
		initQueryMap() ;
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(AOD_UEL)
				.build();

		// Create an instance of our GitHub API interface.
//		VOD vodService = restAdapter.create(VOD.class);
		VODList vodListService = restAdapter.create(VODList.class);
		List<VideoInfo> video = vodListService.videoInfoList("1", queryMap) ;
		// Fetch and print a list of the contributors to this library.
//		List<VideoInfo> vodSeries = vodService.vodSeries("1");
//		List<VideoInfo> vodSeries1 = vodService.vodSeries("2");
//		List<VideoInfo> vodSeries2 = vodService.vodSeries("3");

		for (VideoInfo v : video) {
			System.out.println("["+v.category_id+"]" +v.title + " (" + v.poster + ")");
		}
//		for (VideoInfo v : vodSeries1) {
//			System.out.println("["+v.category_id+"]" + v.title + " (" + v.poster + ")");
//		}
//		for (VideoInfo v : vodSeries2) {
//			System.out.println("["+v.category_id+"]" + v.title + " (" + v.poster + ")");
//		}
	}

	private static void initQueryMap() {
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
