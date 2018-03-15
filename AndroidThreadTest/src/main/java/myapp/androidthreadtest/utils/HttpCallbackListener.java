package myapp.androidthreadtest.utils;

/**
 * Created by Administrator on 2018/3/15.
 */

public interface HttpCallbackListener {
    void onResponse(String response);

    void onError(Exception ex);
}
