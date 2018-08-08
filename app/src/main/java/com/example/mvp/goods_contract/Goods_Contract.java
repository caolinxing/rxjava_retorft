package com.example.mvp.goods_contract;

import com.example.pojo.GoodsBean;
import com.example.utils.OnHttpCallBack;

import java.util.List;

public interface Goods_Contract {
    interface Model {
        void setUrl(String base, String mulu , GetDataState getDataState);
    }
    interface GetDataState{
        void onSuccess(List<GoodsBean.TuijianBean.ListBean> list);
        void onFaild(String errorMsg);//失败了就调用这个方法,可以传递错误的请求消息给调用者
    }
    interface View {
        void onSuccess(List<GoodsBean.TuijianBean.ListBean> list);
        void onFaild(String errorMsg);//失败了就调用这个方法,可以传递错误的请求消息给调用者
    }

    interface Presenter {
        void setUrl(String base,String mulu);
    }
}
