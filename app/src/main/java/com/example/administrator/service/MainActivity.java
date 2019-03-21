package com.example.administrator.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 开启服务
     */
    private Button mBt;
    /**
     * 关闭服务
     */
    private Button mBt2;
    /**
     * 绑定服务
     */
    private Button mBt3;
    /**
     * 解除绑定
     */
    private Button mBt4;
    private ServiceConnection mConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initView();


        //YouService youService = new YouService("1");

        Intent intent = new Intent(MainActivity.this, YouService.class);

        startService(intent);

        //youService.onHandleIntent(intent);


    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt2.setOnClickListener(this);
        mBt3 = (Button) findViewById(R.id.bt3);
        mBt3.setOnClickListener(this);
        mBt4 = (Button) findViewById(R.id.bt4);
        mBt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                Intent intent = new Intent(this, YouService.class);
                intent.putExtra("d","张柏芝");
                startService(intent);//开启服务

                break;
            case R.id.bt2:
                Intent intent2 = new Intent(this, YouService.class);
                stopService(intent2);//停止服务
                break;
            case R.id.bt3:
                bindSer();//绑定服务
                break;
            case R.id.bt4://接触绑定
                if(mConn!=null){
                    //unbindService(mConn);
                    unbindService(mConn);
                    mConn = null;
                }
                break;
        }
    }

    private void bindSer() {
        Intent intent = new Intent(this,YouService.class);
        intent.putExtra("d2","王祖贤");
        mConn = new ServiceConnection() {
            @Override
            //服务绑定成功之后会调用
            public void onServiceConnected(ComponentName name, IBinder service) {
                //  IBinder（service）  就是用来接收服务端传递过来的数据
                //创建内部类对象,调用自己的方法 ===》 外部类.内部类 = service
                     Myservice.MyBinder myBinder = (Myservice.MyBinder) service;
                     myBinder.call();
                    // Myservice.phone();
            }

            @Override
            //服务失去连接时会调用
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        //绑定服务
        bindService(intent, mConn,BIND_AUTO_CREATE);
    }
}
