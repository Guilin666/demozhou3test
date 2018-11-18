package bwie.example.com.demozhou3test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.model.GoodBean;

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.MyViewHolder> {
    private Context context;
    private List<GoodBean.DataBean> data = new ArrayList<>();
    private SellerListener listener;

    public SellerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.seller_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_seller.setText(data.get(i).getSellerName());
        GoodAdapter goodAdapter = new GoodAdapter(context, data.get(i).getList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolder.recycle_good.setAdapter(goodAdapter);
        myViewHolder.recycle_good.setLayoutManager(linearLayoutManager);
        myViewHolder.seller_xuan.setChecked(data.get(i).isIscheck());
        goodAdapter.setGoodListener(new GoodAdapter.GoodListener() {
            @Override
            public void goodChange() {
                //将数据回吊
                listener.SellerChange(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setList(List<GoodBean.DataBean> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_seller;
        RecyclerView recycle_good;
        CheckBox seller_xuan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_seller = itemView.findViewById(R.id.tv_seller);
            recycle_good = itemView.findViewById(R.id.recycle_good);
            seller_xuan=itemView.findViewById(R.id.seller_xuan);
        }
    }

    public void SellerListener(SellerListener listener){
        this.listener=listener;

    }
    public interface SellerListener{
        void SellerChange(List<GoodBean.DataBean> data);
    }
}
