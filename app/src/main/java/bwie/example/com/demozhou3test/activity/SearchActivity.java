package bwie.example.com.demozhou3test.activity;

import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.mvp.presenter.BaseActivityPresenter;
import bwie.example.com.demozhou3test.presenter.SearchActivityPresenter;

public class SearchActivity extends BaseActivityPresenter<SearchActivityPresenter> {
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.tv_search)
    TextView tv_search;
    @Override
    public Class getDegate() {

        return SearchActivityPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        degate.initView(ed_search,tv_search);
    }

    @Override
    protected void onResume() {
        super.onResume();
        degate.onResume();
    }
}
