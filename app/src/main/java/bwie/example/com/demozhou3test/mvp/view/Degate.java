package bwie.example.com.demozhou3test.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface Degate {
    void initData();
    View getRootView();
    void setContext(Context context);
    void create(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);
}
