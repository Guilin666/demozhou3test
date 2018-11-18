package bwie.example.com.demozhou3test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.model.GoodBean;
import bwie.example.com.demozhou3test.view.ShopView;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.MyViewHolder> {


    private  List<GoodBean.DataBean.ListBean> list=new ArrayList<>();
    private  Context context;
    private GoodListener listener;

    public GoodAdapter(Context context, List<GoodBean.DataBean.ListBean> list) {
        this.context=context;
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.good_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.good_img.setImageURI(list.get(i).getImages().split("\\|")[0]);
        myViewHolder.good_num.setText(list.get(i).getNum()+"");
        myViewHolder.good_title.setText(list.get(i).getTitle());
        myViewHolder.good_price.setText(list.get(i).getPrice()+"");
        myViewHolder.xuan.setChecked(list.get(i).isIscheck());
        //回显数量
        myViewHolder.shopview.setData(i,list,context,this);



        //点击单个条目是将选中变为不选中将不选中变成选中
        myViewHolder.xuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischeck = list.get(i).isIscheck();
                //将选中变为不选中将不选中变成选中
                if (ischeck) {
                    list.get(i).setIscheck(false);
                }else{
                    list.get(i).setIscheck(true);
                }
                //回调状态
                listener.goodChange();
                //刷新下标
                notifyItemChanged(i);

            }
        });

        myViewHolder.shopview.setShopViewListener(new ShopView.ShopViewListener() {
            @Override
            public void shopViewChange() {
                listener.goodChange();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView good_img;
        TextView good_num;
        TextView good_price;
        TextView good_title;
        CheckBox xuan;
        ShopView shopview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            good_img=itemView.findViewById(R.id.good_img);
            good_num= itemView.findViewById(R.id.good_num);
            good_price=itemView.findViewById(R.id.good_price);
            good_title=itemView.findViewById(R.id.good_title);
            xuan=itemView.findViewById(R.id.xuan);
            shopview=itemView.findViewById(R.id.shopview);
        }
    }

    public void setGoodListener(GoodListener listener){
        this.listener=listener;
    }

    public interface GoodListener{
        void goodChange();
    }
}
