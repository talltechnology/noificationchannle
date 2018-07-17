package com.example.wxy.noificationchannle.base.basefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by WXY on 2018/7/7.
 */

public abstract class BaseFragment extends Fragment{


    private Unbinder unbinder;
    public View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = getLayoutInflater().inflate(findlayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract int findlayout();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public <T>void ShowToas(T info){
        Toast.makeText(getContext(),""+info,Toast.LENGTH_LONG).show();
    }

}
