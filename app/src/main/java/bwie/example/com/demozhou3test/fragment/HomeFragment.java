package bwie.example.com.demozhou3test.fragment;

import android.widget.CheckBox;

import butterknife.BindView;
import butterknife.OnClick;
import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.mvp.presenter.BaseFragmentPresenter;
import bwie.example.com.demozhou3test.presenter.HomeFragmentPresenter;

public class HomeFragment extends BaseFragmentPresenter<HomeFragmentPresenter>{
    @BindView(R.id.quan)
    CheckBox quan;
    @OnClick(R.id.quan)
    public void onClick(){
        degate.onClick();
    }


    @Override
    public Class getDegate() {
        return HomeFragmentPresenter.class;
    }


    @Override
    public void initView() {
        super.initView();
        degate.initView(quan);
    }


}
