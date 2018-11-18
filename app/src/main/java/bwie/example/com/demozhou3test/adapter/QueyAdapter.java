package bwie.example.com.demozhou3test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.model.QueryBean;

public class QueyAdapter extends RecyclerView.Adapter<QueyAdapter.MyViewHolder> {
    private Context context;
    private List<QueryBean.DataBean> list=new ArrayList<>();
    public QueyAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.query_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.good_img2.setImageURI(list.get(i).getImages().split("\\|")[0]);
        myViewHolder.good_price2.setText(list.get(i).getPrice()+"");
        myViewHolder.good_title2.setText(list.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<QueryBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView good_img2;
        TextView good_price2;
        TextView good_title2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            good_img2=itemView.findViewById(R.id.good_img2);
            good_price2=itemView.findViewById(R.id.good_price2);
            good_title2=itemView.findViewById(R.id.good_title2);
        }
    }
}
