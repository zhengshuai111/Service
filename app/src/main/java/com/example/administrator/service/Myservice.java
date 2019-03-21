package com.example.administrator.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2018/11/6.
 */

public class Myservice extends Service {
    private static final String TAG ="Myservice" ;

    @Nullable
    @Override
    //绑定服务
    //从Service端传递数据到 Acitivity  ，需要在onBind方法中进行传递
    //此方法返回的是IBinder对象会被Acitivity中ServiceConnection接收
    //而传递的数据就会被IBinder对象携带过去 , 由于IBinder是接口
    //所以需要它的实现类 , 并且在实现类中定义数据 ，发送过去。

    public IBinder onBind(Intent intent) {
        String d2 = intent.getStringExtra("d2");
        Log.d(TAG,"onBind:"+d2);
        return new MyBinder();
    }
        class MyBinder extends Binder{
            public void call(){
                phone();
            }
        }

    public  void phone() {
        Log.d(TAG,"==========>"+"李嘉欣在此处");
    }

    //解除绑定
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG," onUnbind:");
        return super.onUnbind(intent);
    }

    @Override
    //开启或者绑定服务时调用
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    //开启服务时调用
    public int onStartCommand(Intent intent, int flags, int startId) {
        String d = intent.getStringExtra("d");
        Log.d(TAG, "onStartCommand: "+d);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    //关闭服务或者解除绑定时调用
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

}
