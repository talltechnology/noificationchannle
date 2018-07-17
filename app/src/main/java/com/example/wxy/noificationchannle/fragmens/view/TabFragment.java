package com.example.wxy.noificationchannle.fragmens.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wxy.noificationchannle.R;
import com.example.wxy.noificationchannle.fragmens.adpter.TabAdpter;
import com.example.wxy.noificationchannle.fragmens.bean.TabBean;
import com.example.wxy.noificationchannle.fragmens.bean.TabDataBean;
import com.example.wxy.noificationchannle.fragmens.bean.ZxingBean;
import com.example.wxy.noificationchannle.fragmens.moder.TabM;
import com.example.wxy.noificationchannle.fragmens.presenter.TabP;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WXY on 2018/7/16.
 */

public class TabFragment extends Fragment implements TabCallback {

    private View view;
    private int page = 1;
    private TabP tabP;
    private XRecyclerView xRecyclerView;
    private ArrayList<TabDataBean.DataBean.ListBean> listdtat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.tab_layout_fragment, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            page = arguments.getInt("key", 1);
            initView();
            initData();
        }
        return view;
    }

    private void initView() {
        xRecyclerView = view.findViewById(R.id.xrecytab);
    }

    private void initData() {
        listdtat = new ArrayList<>();
        tabP = new TabP();
        tabP.Attach(new TabM(), this);
        tabP.requst(page);
    }


    public void Bunder(Bundle bundle) {
        setArguments(bundle);
    }

    @Override
    public <T> void faliu(T t) {

    }

    @Override
    public void Seecc(TabDataBean dataBean) {

        for (int i = 0; i < dataBean.getData().size(); i++) {
            List<TabDataBean.DataBean.ListBean> list = dataBean.getData().get(i).getList();
            listdtat.addAll(list);
        }
        xRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, XRecyclerView.VERTICAL, false));
        xRecyclerView.setAdapter(new TabAdpter(getContext(), listdtat));

    }
}
