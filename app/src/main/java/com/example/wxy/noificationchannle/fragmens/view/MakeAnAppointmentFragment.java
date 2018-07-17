package com.example.wxy.noificationchannle.fragmens.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wxy.noificationchannle.AddressActivity;
import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.base.basefragment.BaseFragment;
import com.example.wxy.noificationchannle.fragmens.adpter.MakeAdpter;
import com.example.wxy.noificationchannle.fragmens.bean.MakeBean;
import com.example.wxy.noificationchannle.fragmens.moder.MakeM;
import com.example.wxy.noificationchannle.fragmens.moder.SpaceM;
import com.example.wxy.noificationchannle.fragmens.presenter.MakeP;
import com.example.wxy.noificationchannle.soso.view.SOSOActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;

/**
 * Created by WXY on 2018/7/7.
 */

public class MakeAnAppointmentFragment extends BaseFragment implements MakeVCallback, XRecyclerView.LoadingListener ,View.OnClickListener{
    @BindView(R.id.xrec)
    public XRecyclerView xrec;
    private MakeAdpter makeAdpter;
    private MakeP makeP;
    private MakeBean makeBean1;
    private boolean falg=true;

    @Override
    protected void initData() {

        makeP = new MakeP();
        makeP.Attach(new MakeM(), this);
        makeP.requstloder();
    }

    @Override
    protected int findlayout() {
        return R.layout.makeanappointmentfragment;
    }

    @Override
    public <T> void faliu(T t) {
        ShowToas(t);
    }

    @Override
    public void seecc(MakeBean makeBean) {

        if (makeAdpter == null) {
            this.makeBean1 = makeBean;
            xrec.setLayoutManager(new LinearLayoutManager(getContext(), XRecyclerView.VERTICAL, false));
            makeAdpter = new MakeAdpter(getContext(),this);
            makeAdpter.setMakebenan(makeBean);
            xrec.setAdapter(makeAdpter);
            //支持上下拉
            xrec.setLoadingMoreEnabled(true);
            xrec.setPullRefreshEnabled(true);
            xrec.setLoadingListener(this);//添加上下拉监听
        } else {
            makeBean1.getData().addAll(makeBean.getData());
            if(falg){
                falg=false;
                this.makeBean1=makeBean;
                xrec.refreshComplete();
            }
            makeAdpter.setMakebenan(makeBean1);
            makeAdpter.notifyDataSetChanged();
            xrec.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        makeP.requstloder();
        falg=true;
    }

    @Override
    public void onLoadMore() {
        makeP.requstloder();
        falg=false;
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.nake_address:
               startActivity(new Intent(getContext(), AddressActivity.class));
               break;
       }
    }

}
