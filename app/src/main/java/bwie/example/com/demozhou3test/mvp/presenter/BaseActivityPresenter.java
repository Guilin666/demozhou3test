package bwie.example.com.demozhou3test.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import bwie.example.com.demozhou3test.mvp.view.DegateImpl;



public abstract class BaseActivityPresenter<T extends DegateImpl> extends AppCompatActivity {


    public T degate;

    public abstract Class<T> getDegate();

    public BaseActivityPresenter() {
        try {
             degate = getDegate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        degate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(degate.getRootView());
        ButterKnife.bind(this);
        initView();
        degate.setContext(this);

        degate.initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        degate.destory();
        degate=null;

    }


    //查找控件
    public void initView(){

    }


}
