package bwie.example.com.demozhou3test.activity;

import bwie.example.com.demozhou3test.mvp.presenter.BaseActivityPresenter;
import bwie.example.com.demozhou3test.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter>{



    @Override
    public Class<MainActivityPresenter> getDegate() {
        return MainActivityPresenter.class;
    }





}
