package bwie.example.com.demozhou3test.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpUtils {
    public static final String BASE_URL = "http://www.zhaoapi.cn/";
    private static volatile HttpUtils instance;
    private final Retrofit build;

    //初始化
    private HttpUtils() {
        build = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client()).build();
    }

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (null == instance) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }


    private OkHttpClient client() {
        //添加拉拦截器
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response proceed = chain.proceed(chain.request());
                return proceed;
            }
        }).build();
    }


    public <T> T create(Class<T> tclass) {
        return build.create(tclass);
    }

}
