package bwie.example.com.demozhou3test.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.activity.ShowActivity;
import bwie.example.com.demozhou3test.adapter.QueyAdapter;
import bwie.example.com.demozhou3test.model.GoodBean;
import bwie.example.com.demozhou3test.model.QueryBean;
import bwie.example.com.demozhou3test.mvp.view.DegateImpl;
import bwie.example.com.demozhou3test.net.HttpUtil;
import bwie.example.com.demozhou3test.utils.Api;
import bwie.example.com.demozhou3test.utils.HttpUtils;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowActivityPresenter extends DegateImpl {

    private String data;
    private QueyAdapter queyAdapter;
    private RecyclerView show_recycle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    public void initData() {
        super.initData();
        show_recycle = (RecyclerView) get(R.id.show_recycle);
        data = ((ShowActivity) context).getIntent().getStringExtra("data");
        queyAdapter = new QueyAdapter(context);
        doHttp();

    }

    private void doHttp() {
        Map<String,String> map = new HashMap<>();
        map.put("keywords",data);
        new HttpUtil().get("/product/searchProducts",map).result(new HttpUtil.HttpListener() {
            @Override
            public void success(String data) {

                QueryBean queryBean = new Gson().fromJson(data, QueryBean.class);
                List<QueryBean.DataBean> data1 = queryBean.getData();
                queyAdapter.setList(data1);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                show_recycle.setLayoutManager(linearLayoutManager);
                show_recycle.setAdapter(queyAdapter);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    private Context context;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context=context;
    }
}
