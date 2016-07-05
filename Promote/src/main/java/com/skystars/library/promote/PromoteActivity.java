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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.gson.Gson;
import com.skystars.library.admobadapter.AdmobRecyclerAdapterWrapper;
import com.skystars.library.admobadapter.expressads.AdmobExpressRecyclerAdapterWrapper;

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
//                        mAdapter = new PromoteAdapter(getBaseContext(),mList);
//                        recycler_view.setAdapter(mAdapter);
//
//                        mAdapter.setOnItemClickListener(new PromoteAdapter.OnRecyclerViewItemClickListener(){
//                            @Override
//                            public void onItemClick(View view , int data){
//                                Log.e("click","" + data);
//                                Uri uri = Uri.parse(mList.get(data).getUrl());
//                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                                startActivity(intent);
//                            }
//                        });

                        initRecyclerViewItems(mList);
                    }
                });
            }
        });
    }
    AdmobExpressRecyclerAdapterWrapper adapterWrapper;
    //AdmobRecyclerAdapterWrapper adapterWrapper;
    private void initRecyclerViewItems(final List<PromoteBean> mList) {
//        //creating your adapter, it could be a custom adapter as well
        RecyclerExampleAdapter adapter  = new RecyclerExampleAdapter(this);
//
        adapterWrapper = new AdmobExpressRecyclerAdapterWrapper(this);
//        //TODO it's important to set your test device ID (you can find it in LogCat after launching the debug session i.e. by word "test")
//        //if you launch app on emulator and experience some troubles
//        // try passing the constant AdRequest.DEVICE_ID_EMULATOR
//        adapterWrapper.setTestDeviceId(getString(R.string.testDeviceID));//set an unique test device ID
//        //TODO set the custom ads layout if you wish. NOTE you have to set your admob unit ID in this XML.
//        //It doesn't work for me if I set the unit ID in code with the method setAdUnitID() so it seems to be a bug
//        //adapterWrapper.setExpressAdsLayoutId(R.layout.adexpresslistview_item);
        adapterWrapper.setAdapter(adapter); //wrapping your adapter with a AdmobExpressRecyclerAdapterWrapper.
//
//        //Sets the max count of ad blocks per dataset, by default it equals to 3 (according to the Admob's policies and rules)
        adapterWrapper.setLimitOfAds(3);
//
//        //Sets the number of your data items between ad blocks, by default it equals to 10.
//        //You should set it according to the Admob's policies and rules which says not to
//        //display more than one ad block at the visible part of the screen,
//        // so you should choose this parameter carefully and according to your item's height and screen resolution of a target devices
        adapterWrapper.setNoOfDataBetweenAds(4);
//
//        //It's a test admob ID. Please replace it with a real one only when you will be ready to deploy your product to the Release!
//        //Otherwise your Admob account could be banned
//        String admobUnitId = getResources().getString(R.string.banner_admob_unit_id);
//        adapterWrapper.setAdmobReleaseUnitId(admobUnitId);
//
        recycler_view.setAdapter(adapterWrapper); // setting an AdmobExpressRecyclerAdapterWrapper to a RecyclerView
//
        adapter.setOnItemClickListener(new RecyclerExampleAdapter.OnRecyclerViewItemClickListener(){
                            @Override
                            public void onItemClick(View view , int data){
                                Log.e("click","" + data);
                                Uri uri = Uri.parse(mList.get(data).getUrl());
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        });
//
//        //adding a collection of data to your adapter and rising the data set changed event
        adapter.addAll(mList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        adapterWrapper.destroyAds();
    }
}