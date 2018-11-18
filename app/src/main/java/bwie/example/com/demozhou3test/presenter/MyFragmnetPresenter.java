package bwie.example.com.demozhou3test.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.activity.SearchActivity;
import bwie.example.com.demozhou3test.mvp.view.DegateImpl;
import bwie.example.com.demozhou3test.view.GoodView;

public class MyFragmnetPresenter extends DegateImpl {
//    private List<String> mDatas=new ArrayList<>();


    private SearchView serch;

    @Override
    public int getLayoutId() {
        return R.layout.myfragment_layout;
    }

    @Override
    public void initData() {
        super.initData();
        GoodView goodview = (GoodView) get(R.id.goodview);
        serch.setIconifiedByDefault(false);
        serch.setSubmitButtonEnabled(true);
        serch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(context, SearchActivity.class);
                intent.putExtra("data", query);
                context.startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

    }

    private Context context;

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context = context;


    }

    public void initView(SearchView serch) {
        this.serch = serch;
    }

    public void doclick() {

        toast("点击了");
    }
}

