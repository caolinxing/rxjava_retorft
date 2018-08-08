package com.example.utils;

import com.example.pojo.GoodsBean;

import retrofit2.http.GET;
import rx.Observable;

public interface GoodsApi {
    @GET("ad/getAd")
    /*Call<GoodsBean> rxGoods();*/
    Observable<GoodsBean> rxGoods();
}
