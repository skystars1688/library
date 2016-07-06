package com.skystars.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.skystars.library.bulletin.BulletinDialog;
import com.skystars.library.promote.PromoteActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton btn = (AppCompatButton) findViewById(R.id.btnDialog);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient mOkHttpClient=new OkHttpClient();
                Request.Builder requestBuilder = new Request.Builder().url("http://skystars.tw/app.html");
                requestBuilder.method("GET",null);
                Request request = requestBuilder.build();
                Call mcall= mOkHttpClient.newCall(request);
                mcall.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (null != response.cacheResponse()) {
                        } else if(null != response.networkResponse()){
                            try {
                                BulletinDialog.newInstance(MainActivity.this, response.body().string(), R.layout.dialog_bulletin)
                                        .show(getSupportFragmentManager(),"B");
                            }catch (Exception e){}
                        }
                    }
                });
            }
        });

        AppCompatButton btnP = (AppCompatButton) findViewById(R.id.btnPromote);
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,PromoteActivity.class);
                intent.putExtra(PromoteActivity.URL,"http://skystars.tw/promote.json");
                startActivity(intent);
            }
        });
    }
}
