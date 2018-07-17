package com.example.wxy.noificationchannle.soso.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.soso.adpter.SOSOAdapter;
import com.example.wxy.noificationchannle.soso.bean.SOSOBean;
import com.example.wxy.noificationchannle.soso.moder.SOSOMDER;
import com.example.wxy.noificationchannle.soso.presenter.SOSOP;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SOSOActivity extends AppCompatActivity implements SOSOCallBack, XRecyclerView.LoadingListener,View.OnClickListener{

    private Unbinder unbinder;
    @BindView(R.id.sosorecy)
    public XRecyclerView sosorecy;
    private SOSOP sosop;
    private int pager;
    private SOSOAdapter sosoAdapter;
    private SOSOBean sosoBean1;
    private boolean flage=true;
    private String s="笔记本";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soso);
        unbinder = ButterKnife.bind(this);
        inData();
    }


    private void inData() {
        sosop = new SOSOP();
        sosop.Attach(new SOSOMDER(),this);
        pager=1;
        Log.e("笔记本","------------------------------------------------"+pager);
        sosop.requster("笔记本",pager);
        //支持上下拉

    }

    @Override
    public <T> void faliu(T t) {
        Toast.makeText(this, "获取失败" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void Seecc(SOSOBean sosoBean) {
        if(sosoAdapter==null){
            sosoBean1=sosoBean;
            sosorecy.setLayoutManager(new LinearLayoutManager(this));
            sosoAdapter = new SOSOAdapter(this, sosoBean, this);
            sosorecy.setAdapter(sosoAdapter);
            sosorecy.setLoadingMoreEnabled(true);
            sosorecy.setPullRefreshEnabled(true);
            sosorecy.setLoadingListener(this);//添加上下拉监听
        }else {
            if(flage){
                List<SOSOBean.DataBean> data = sosoBean1.getData();
                sosoBean1.getData().addAll(data);
                sosoAdapter.setSeeSoso(sosoBean1);
                sosoAdapter.notifyDataSetChanged();
                sosorecy.loadMoreComplete();
            }else {
                sosoBean1=sosoBean;
                sosoAdapter.setSeeSoso(sosoBean1);
                sosoAdapter.notifyDataSetChanged();
                sosorecy.refreshComplete();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        flage=false;
        pager=1;
        sosop.requster(s,pager);
    }

    @Override
    public void onLoadMore() {
        flage=true;
        pager++;
        sosop.requster(s,pager);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.soso_soso_text:
               s = ((EditText) v.getTag(R.id.soso_id)).getText().toString();
               pager=1;
               sosop.requster(s,pager);
               break;
           case R.id.soso_ht:
               finish();
               break;
       }
    }
}
