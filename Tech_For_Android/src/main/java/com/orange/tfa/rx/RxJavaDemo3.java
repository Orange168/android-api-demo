package com.orange.tfa.rx;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Edward.Lin on 2015/7/2 15:33
 */
public class RxJavaDemo3 {

    public static void main(String[] args) {

        Observable.just("Error isn't it")
                .map(RxJavaDemo3::potentialError)
                .map(RxJavaDemo3::potentialException)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String string) {
                        System.out.println(string);
                    }
                }) ;
//                .subscribe(System.out::println) ;
        //2. Schedulers
        mObservableService
                .subscribeOn(Schedulers.io()) //set the subscribe run on thread
//                .observeOn(AndroidSchedulers.mainThread()) //set the observe run on
                .subscribe()
                .unsubscribe() ; // unsubscribe

    }

    private static String potentialError(String s){
        System.out.println(s);
        return s+" is [pass]" ;
    }
    private static String potentialException(String s){
        System.out.println(s);
        return s + " is [pass++]";
    }

    private static Observable<String> mObservableService = Observable.just("hello world");
}
