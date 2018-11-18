package bwie.example.com.demozhou3test.utils;


import java.util.Map;

import bwie.example.com.demozhou3test.model.GoodBean;
import bwie.example.com.demozhou3test.model.QueryBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Api {
    @GET("product/getCarts?uid=71")
    Observable<GoodBean> create();//@QueryMap Map<String,String> map
    @GET("product/searchProducts?")//keywords=笔记本&page=1
    Observable<QueryBean> query(@QueryMap Map<String,String> map);

}
