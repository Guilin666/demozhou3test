package bwie.example.com.demozhou3test.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class DegateImpl <T extends View>implements Degate {

    private Context context;

    private SparseArray<View> views=new SparseArray<>();

    private View rootView;

    public  T get(int id){
        T view = (T) views.get(id);
        if (view == null) {
            view=rootView.findViewById(id);
            views.put(id,view);
        }
        return view;
    }


    public void setOnclick(View.OnClickListener listener,int...ids){
        if (ids == null) {
            return;
        }
        for (int id:ids) {
            get(id).setOnClickListener(listener);
        }
    }




    @Override
    public void initData() {

    }


    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }


    public void toast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void create(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = layoutInflater.inflate(getLayoutId(),viewGroup, false);
    }

    public abstract int getLayoutId();


    public void destory() {
        rootView=null;
    }
}
