package bwie.example.com.demozhou3test.fragment;

import android.widget.SearchView;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.mvp.presenter.BaseFragmentPresenter;
import bwie.example.com.demozhou3test.presenter.MyFragmnetPresenter;

public class MyFragmnet extends BaseFragmentPresenter<MyFragmnetPresenter> {
    @BindView(R.id.serch)
    SearchView serch;

    @OnClick(R.id.serch)
    public void click(){
        degate.doclick();
    }




    @Override
    public Class getDegate() {
        return MyFragmnetPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        degate.initView(serch);
    }


}
