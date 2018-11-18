package bwie.example.com.demozhou3test.activity;

import bwie.example.com.demozhou3test.mvp.presenter.BaseActivityPresenter;
import bwie.example.com.demozhou3test.presenter.ShowActivityPresenter;

public class ShowActivity extends BaseActivityPresenter <ShowActivityPresenter>{


    @Override
    public Class getDegate() {
        return ShowActivityPresenter.class;
    }
}
