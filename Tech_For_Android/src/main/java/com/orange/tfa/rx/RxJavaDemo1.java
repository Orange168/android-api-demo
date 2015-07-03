package com.orange.tfa.rx;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Edward Lin
 * on 7/1/15 11:24 PM.
 */
public class RxJavaDemo1 {



	public static void main(String[] args) {
		//1.the sample  is most easy
		Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				subscriber.onNext("hello world");
				subscriber.onCompleted();
			}
		}) ;

		Subscriber<String> mSubscriber = new Subscriber<String>() {
			@Override
			public void onCompleted() {}

			@Override
			public void onError(Throwable e) {}

			@Override
			public void onNext(String s) {System.out.println(s);}
		} ;

		mObservable.subscribe(mSubscriber);
		//1.2 abbreviate
		// TODO: 7/2/15 myObservable.subscribe(onNextAction, onErrorAction, onCompleteAction);
		Observable<String> mObservable2 = Observable.just("Hello RxJava") ;
		Action1<String> onNextAction = new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println(s) ;
			}
		} ;
		mObservable2.subscribe(onNextAction);
		//1.3  simpler code
		Observable.just("simpler code").subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				System.out.println(s) ;
			}
		});
		//1.4. Java8 lambda
		Observable.just("Hello Rx and lambda").subscribe(s -> System.out.println(s)) ;
		//2. transformation
		Observable.just("Hello trans").subscribe(s -> System.out.println(s+"formation")) ;
		//2.1 map()
		Observable.just("Hello trans").map(new Func1<String, String>() {
			@Override
			public String call(String s) {
				return s+"formation";
			}
		}).subscribe(s -> System.out.println(s));
//		}).subscribe(System.out::println);
		//2.2 trans + lambda
		Observable.just("Hello trans").map(s -> s+"fromation").subscribe(s -> System.out.println(s));
		//or
		Observable.just("Hello trans").map(s -> s+"fromation").subscribe(System.out::println);
		//2.3 trans String to Integer
		Observable.just("Hello trans!").map(new Func1<String, Integer>() {
			@Override
			public Integer call(String s) {
				return s.hashCode();
			}
		}).subscribe(i-> System.out.println(Integer.toString(i)));
		Observable.just("Hello trans!").map(s -> s.hashCode())
				.subscribe(i -> System.out.println(Integer.toString(i)));
		Observable.just("Hello trans!").map(String::hashCode)
				.subscribe(i->System.out.println(Integer.toString(i)));
		Observable.just("Hello trans!").map(String::hashCode).map(i->Integer.toString(i))
				.subscribe(System.out::println);
	}
}
