package bwie.example.com.demozhou3test.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpUtil {

    private final BaseService baseService;
    private Observable<ResponseBody> observable;

    public HttpUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://www.zhaoapi.cn/")
                .build();
        baseService = retrofit.create(BaseService.class);
    }

    public HttpUtil get(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        observable = baseService.get(url, map);
        setObservable();
        return this;
    }


    public HttpUtil post(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        observable = baseService.post(url, map);
        setObservable();
        return this;
    }

    private void setObservable() {
        observable.subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private Observer observer = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String string = responseBody.string();
                listener.success(string);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onError(Throwable e) {
            String message = e.getMessage();
            listener.fail(message);
        }

        @Override
        public void onComplete() {

        }
    };
    private HttpListener listener;

    public void result(HttpListener listener) {
        this.listener = listener;
    }

    public interface HttpListener {
        void success(String data);

        void fail(String error);
    }

}

