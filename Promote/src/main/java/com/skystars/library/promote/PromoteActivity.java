package com.skystars.library.promote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.skystars.library.promote.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 文 件 名: PromoteActivity
 * 创 建 人: TOO15
 * 创建日期: 2016/6/30 18:49
 * 修改时间：
 * 修改备注：
 */
public class PromoteActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    PromoteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_promote);

        getSupportActionBar().setTitle(R.string.title_promote);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
        getAsynHttp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void adv(){
//        NativeExpressAdView adView = (NativeExpressAdView) findViewById(R.id.adView);
//        adView.loadAd(new AdRequest.Builder().build());
    }

    private void initView(){
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(llm);
    }

    private void getAsynHttp() {
        final List<PromoteBean> mList = new ArrayList<PromoteBean>();
        final Gson gson = new Gson();

        OkHttpClient mOkHttpClient=new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://skystars.tw/promote.json");
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
                    //String str = response.cacheResponse().toString();
                    //Log.e("wangshu", "cache---" + str);
                } else if(null != response.networkResponse()){
//                    response.body().string();
                    //String str = response.networkResponse().toString();
//                    Log.e("wangshu", "network---" + response.body().string());
                    try {
                        PromoteBean[] jsonArray = gson.fromJson(response.body().string(), PromoteBean[].class);

                        for (PromoteBean item : jsonArray) {
                            mList.add(item);
                        }
                    }catch (Exception e){}
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new PromoteAdapter(getBaseContext(),mList);
                        recycler_view.setAdapter(mAdapter);

                        mAdapter.setOnItemClickListener(new PromoteAdapter.OnRecyclerViewItemClickListener(){
                            @Override
                            public void onItemClick(View view , int data){
                                Log.e("click","" + data);
                                Uri uri = Uri.parse(mList.get(data).getUrl());
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
    }
}