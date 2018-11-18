package bwie.example.com.demozhou3test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.example.com.demozhou3test.R;
import bwie.example.com.demozhou3test.adapter.GoodAdapter;
import bwie.example.com.demozhou3test.model.GoodBean;

public class ShopView extends RelativeLayout{


    private EditText edd_text;
    private Context context;
    private GoodAdapter goodAdapter;
    private List<GoodBean.DataBean.ListBean> list=new ArrayList<>();
    private int num;
    private ShopViewListener listener;
    private int i;

    public ShopView(Context context) {
        super(context);
        init(context);
    }

    public ShopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        View inflate = View.inflate(context, R.layout.shopview_layout, null);
        ImageView add=(ImageView)inflate.findViewById(R.id.add);
        ImageView jian=(ImageView)inflate.findViewById(R.id.jian);
        edd_text = (EditText)inflate.findViewById(R.id.edd_text);
        addView(inflate);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                edd_text.setText(num+"");
                list.get(i).setNum(num);
                goodAdapter.notifyItemChanged(i);
                listener.shopViewChange();

            }
        });

        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num>1) {
                    num--;
                    edd_text.setText(num+"");
                }else{
                    Toast.makeText(context, "至少选择一个商品", Toast.LENGTH_SHORT).show();
                }
                list.get(i).setNum(num);
                goodAdapter.notifyItemChanged(i);
                listener.shopViewChange();
            }
        });
    }

    public void setData(int i, List<GoodBean.DataBean.ListBean> list, Context context, GoodAdapter goodAdapter) {
        this.i=i;
        num = list.get(i).getNum();
        edd_text.setText(num +"");
        this.context=context;
        this.goodAdapter=goodAdapter;
        this.list=list;
        
    }


    public void setShopViewListener(ShopViewListener listener){
        this.listener=listener;
    }
    public interface ShopViewListener{
        void shopViewChange();
    }


}
