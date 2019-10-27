package com.tanjiajun.rxjavademo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TanJiaJun on 2019-10-27.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("Tan");
            emitter.onNext("Jia");
            emitter.onNext("Jun");
            emitter.onComplete();

            Log.i("TanJiaJun", "subscribe方法所在的线程：" + Thread.currentThread().getName());
        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("TanJiaJun", "onSubscribe方法所在的线程：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("TanJiaJun", "onNext方法所在的线程：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TanJiaJun", "onError所在的线程：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("TanJiaJun", "onComplete方法所在的线程：" + Thread.currentThread().getName());
                    }
                });
    }

}