package bwie.example.com.demozhou3test.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import bwie.example.com.demozhou3test.mvp.view.DegateImpl;

public abstract class BaseFragmentPresenter<T extends DegateImpl> extends Fragment {


    public T degate;
    private Unbinder bind;

    public abstract Class<T> getDegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //获取每一个Presenter对象
            degate = getDegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        degate.create(inflater, container, savedInstanceState);
        return degate.getRootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bind = ButterKnife.bind(this, getView());
        initView();
        degate.setContext(getActivity());
        degate.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        degate.destory();//销毁视图
        degate = null;

    }

    public void initView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
