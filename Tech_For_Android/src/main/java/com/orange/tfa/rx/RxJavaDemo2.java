package com.orange.tfa.rx;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Edward.Lin on 2015/7/2 12:17
 */
public class RxJavaDemo2 {
    // TODO: 2015/7/2  The only limit is yourself

    private static List<String> list;

    public static void main(String[] args) {


        list = new ArrayList<>();
        list.add("url1");
        list.add("url2");
        list.add("url3");
        list.add("url4");
        list.add("url5");
        //Init
        Observable.from(list).subscribe(System.out::println) ;
        // search and return List<URL>
        query("Search Hello").subscribe(urls->{
            for(String url:urls) System.out.println(url);
        }) ;
        //1.1 flatMap usage
        query("1.1 first flatMap").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> list) {
                return Observable.from(list);
            }
        }).subscribe(System.out::println);

        //1.2 lambda + flatMap
        query("flatMap and lambda ").flatMap(list->Observable.from(list)).subscribe(s -> System.out.println(s));
        //or
        query("flatMap and lambda ").flatMap(Observable::from).subscribe(System.out::println);
        //1.3 two flatMap
        query("1.3 two flatMap").flatMap(Observable::from).flatMap(s -> getTitle(s)).subscribe(System.out::println);
        query("1.3 two flatMap").flatMap(Observable::from).flatMap(RxJavaDemo2::getTitle).subscribe(System.out::println);
        //1.4 filter
        query("1.4 filter").flatMap(Observable::from).flatMap(RxJavaDemo2::getTitle)
                .filter(title->title != null)
                .subscribe(System.out::println);
        //1.5 take only show 5 results at most
        query("1.5 take(3)").flatMap(Observable::from).flatMap(RxJavaDemo2::getTitle)
                .filter(title->title != null)
                .take(3)
                .subscribe(System.out::println);

    }

    public static Observable<String> getTitle(String s){
        return Observable.create(subscriber -> {
            if ("url2".equals(s)){
                subscriber.onNext(null);
            }else {
                subscriber.onNext("Title=["+s+"]");
            }
            subscriber.onCompleted();
        });
    }

    /**
     * Returns a List of website URLs based on a text search
     * @param text search info
     * @return
     */
    public static Observable<List<String>> query(String text) {
        System.out.println("Search (" +text +")");
        return Observable.create(new Observable.OnSubscribe<List<String>>() {

            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
    }
}
