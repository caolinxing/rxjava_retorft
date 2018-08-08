package com.example.mvp.goods_model;

import com.example.mvp.goods_contract.Goods_Contract;
import com.example.pojo.GoodsBean;
import com.example.utils.GoodsApi;
import com.example.utils.RetrofitUtils;
import com.socks.library.KLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Goods_Model implements Goods_Contract.Model {
    @Override
    public void setUrl(String url, String mulu, final Goods_Contract.GetDataState callBack) {
        //登录的网络请求
        RetrofitUtils.newInstence(url)//实例化Retrofit对象
                .create(GoodsApi.class)//创建Rxjava---->LoginService对象
                .rxGoods()
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<GoodsBean>() {//网络(登录)请求回调
                    @Override
                    public void onCompleted() {
                        //完成的时候调用
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.getMessage() + "--");
                        e.printStackTrace();
                        //失败的时候调用-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                            KLog.e("Myloy", e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        callBack.onSuccess(goodsBean.getTuijian().getList());
                    }
                });
    }

}
