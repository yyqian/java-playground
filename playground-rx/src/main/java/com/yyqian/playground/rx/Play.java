package com.yyqian.playground.rx;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 2017-01-23T10:18:45+08:00.
 *
 * @author Yinyin Qian
 */
public class Play {

    private static final ExecutorService executor =
            Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws InterruptedException {
        play3();
        executor.shutdown();
    }

    private static void play2() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                print("Completed!");
            }

            @Override
            public void onError(Throwable throwable) {
                print("Error!");
            }

            @Override
            public void onNext(String s) {
                print("Item: " + s);
            }
        };
        Observable<String> observable = Observable.create(subscriber1 -> {
            print("1");
            subscriber1.onNext("Hello");
            print("2");
            subscriber1.onNext("Hi");
            print("3");
            subscriber1.onNext("Aloha");
            print("4");
            subscriber1.onNext("你好");
            subscriber1.onCompleted();
        });
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(subscriber);
    }

    private static void play3() {
        Observable<String> ob = Observable.just("a 1", "b 2", "c 3", "a 4", "b 5", "c 6");
        ob.flatMap(str -> Observable.from(str.split(" ")))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(executor))
                .subscribe(Play::print);
    }

    private static void play1() {
        Observable<String> ob = Observable.just("a1", "b2", "c3", "a4", "b5", "c6");
        ob.groupBy(str -> str.charAt(0))
                .subscribe(groupedOb -> groupedOb.observeOn(Schedulers.from(executor)).subscribe(Play::print));
    }

    private static void print(String str) {
        System.out.println(Thread.currentThread().getName() + " | " + str);
    }
}
