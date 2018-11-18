package bwie.example.com.demozhou3test.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.activity.SearchActivity;
import bwie.example.com.demozhou3test.activity.ShowActivity;
import bwie.example.com.demozhou3test.mvp.view.DegateImpl;
import bwie.example.com.demozhou3test.sql.Sqlutils;
import bwie.example.com.demozhou3test.view.GoodView;
// STOPSHIP: 2018/11/17
public class SearchActivityPresenter extends DegateImpl {
    private EditText ed_search;
    private TextView tv_search;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        super.initData();
//        String data = ((SearchActivity) context).getIntent().getStringExtra("data");
//        toast(data);
        //流逝布局
        final GoodView goodview=(GoodView)get(R.id.goodview);
        TextView tv_clear=(TextView)get(R.id.tv_clear);

        final Sqlutils sqlutils = new Sqlutils(context);
        //查询数据库
        List<String> select = sqlutils.select();

        goodview.setList(select);

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = ed_search.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    toast("请输入内容");
                    return;
                }
                sqlutils.insert(trim);//添加数据库
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("data",trim);
                context.startActivity(intent);
                ((SearchActivity)context).finish();

            }
        });
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sqlutils.clear();
                onResume();
            }
        });

    }

    private Context context;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        this.context=context;
    }

    public void initView(EditText ed_search, TextView tv_search) {
        this.ed_search=ed_search;
        this.tv_search=tv_search;
    }

    public void onResume() {
        initData();
    }
}
