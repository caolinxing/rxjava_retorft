package com.example.mvp.goods_presenter;

import com.example.mvp.goods_contract.Goods_Contract;
import com.example.mvp.goods_model.Goods_Model;
import com.example.pojo.GoodsBean;

import java.util.List;

public class Goods_Presenter implements Goods_Contract.Presenter, Goods_Contract.GetDataState {
    private  Goods_Contract.View view;
    private  Goods_Contract.Model model;

    public Goods_Presenter(Goods_Contract.View view) {
        this.view = view;
        model = new Goods_Model();
    }

    @Override
    public void setUrl(String base, String mulu) {
        model.setUrl(base,mulu,this);
    }

    @Override
    public void onSuccess(List<GoodsBean.TuijianBean.ListBean> list) {
        view.onSuccess(list);
    }


    @Override
    public void onFaild(String errorMsg) {
        view.onFaild(errorMsg);
    }
}
