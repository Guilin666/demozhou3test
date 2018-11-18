package bwie.example.com.demozhou3test.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.adapter.SellerAdapter;
import bwie.example.com.demozhou3test.model.GoodBean;
import bwie.example.com.demozhou3test.mvp.view.DegateImpl;
import bwie.example.com.demozhou3test.utils.Api;
import bwie.example.com.demozhou3test.utils.HttpUtils;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeFragmentPresenter extends DegateImpl implements View.OnClickListener {

    private SellerAdapter sellerAdapter;
    private RecyclerView recycle_home;
    CheckBox quan;
    private List<GoodBean.DataBean> data = new ArrayList<>();
    private Button btn_now_num;
    private TextView tv_now_price;
    private TextView tv_edt;
    private Button btn_now_delete;

    @Override
    public int getLayoutId() {
        return R.layout.homefragment_layout;
    }

    @Override
    public void initData() {
        super.initData();
        recycle_home = (RecyclerView) get(R.id.recycle_home);
        btn_now_num = (Button) get(R.id.btn_now_num);
        tv_now_price = (TextView) get(R.id.tv_now_price);
        btn_now_delete = (Button) get(R.id.btn_now_delete);

        tv_edt = (TextView) get(R.id.tv_edt);
        setOnclick(this, R.id.tv_edt, R.id.btn_now_delete);


        sellerAdapter = new SellerAdapter(context);
        doHttp();
        sellerAdapter.SellerListener(new SellerAdapter.SellerListener() {
            @Override
            public void SellerChange(List<GoodBean.DataBean> data) {
                int num = 0;//选中的数量
                int numAll = 0;//总数量
                double price = 0;//价格
                for (int i = 0; i < data.size(); i++) {
                    List<GoodBean.DataBean.ListBean> list = data.get(i).getList();
                    for (int j = 0; j < list.size(); j++) {
                        numAll += list.get(j).getNum();//单个数量
                        if (list.get(j).isIscheck()) {
                            num += list.get(j).getNum();//累加选中的数量
                            price += list.get(j).getPrice() * list.get(j).getNum();
                        }
                    }
                }


                //小于选中不小于就不选中
                if (num < numAll) {
                    quan.setChecked(false);
                } else {
                    quan.setChecked(true);
                }
                btn_now_num.setText(num + "");
                tv_now_price.setText("$" + price + "");
                sellerAdapter.notifyDataSetChanged();


            }
        });

    }

    private void doHttp() {
        try {
            Api api = HttpUtils.getInstance().create(Api.class);
//        Map<String, String> map = new HashMap<>();
//        map.put("uid", "71");
            Observable<GoodBean> goodBeanObservable = api.create();
            goodBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GoodBean>() {
                @Override
                public void accept(GoodBean goodBean) throws Exception {
                    data = goodBean.getData();
                    data.remove(0);
                    sellerAdapter.setList(data);
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycle_home.setLayoutManager(linearLayoutManager);
            recycle_home.setAdapter(sellerAdapter);
        } catch (Exception e) {

        }


    }

    private Context context;

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context = context;
    }


    public void initView(CheckBox quan) {
        this.quan = quan;
    }


    public void onClick() {
        int num = 0;
        double price = 0;
        boolean checked = quan.isChecked();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIscheck(checked);
            List<GoodBean.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setIscheck(checked);
                num += list.get(j).getNum();
                price += list.get(j).getPrice() * list.get(j).getNum();
            }
        }
        if (checked) {
            btn_now_num.setText(num + "");
            tv_now_price.setText("$" + price + "");

        } else {
            btn_now_num.setText("数量");
            tv_now_price.setText("总价：");
        }
        sellerAdapter.notifyDataSetChanged();

    }

    boolean flag = true;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_edt:
                if (flag) {
                    tv_edt.setText("完成");
                    btn_now_delete.setVisibility(View.VISIBLE);
                    btn_now_num.setVisibility(View.GONE);
                    flag = false;
                } else {
                    tv_edt.setText("编辑");
                    btn_now_num.setVisibility(View.VISIBLE);
                    btn_now_delete.setVisibility(View.GONE);
                    flag = true;
                }
                break;
            case R.id.btn_now_delete:
                doDelete();
                break;
        }
    }

    //删除
    private void doDelete() {
        for (int i = 0; i < data.size(); i++) {
            List<GoodBean.DataBean.ListBean> list = data.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                boolean ischeck = list.get(j).isIscheck();
                if (ischeck) {
                    list.remove(j);
                }
            }
            if (list.size()==0) {
                data.remove(i);
            }

        }
        if (quan.isChecked()) {
            data.clear();
        }
        sellerAdapter.notifyDataSetChanged();


    }
}
