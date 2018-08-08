package com.example.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.example.adapter.XrecyclerAdater;
import com.example.mvp.goods_contract.Goods_Contract;
import com.example.mvp.goods_presenter.Goods_Presenter;
import com.example.utils.GoodsApi;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.example.pojo.GoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class MainActivity extends  AppCompatActivity implements Goods_Contract.View {
    public static final String api = "http://120.27.23.105/";
    public static final String mulu = "ad/getAd";
    private List<GoodsBean.TuijianBean.ListBean> goodsList = new ArrayList<>();
    @BindView(R.id.xRecycler)
    XRecyclerView xRecycler;
    private Goods_Contract.Presenter presenter;
    private XrecyclerAdater adater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        xRecycler.setLayoutManager(new GridLayoutManager(this,2));
        presenter = new Goods_Presenter(this);
        presenter.setUrl(api,mulu);
        adater = new XrecyclerAdater(MainActivity.this, goodsList);
        xRecycler.setAdapter(adater);
    }

    @Override
    public void onSuccess(List<GoodsBean.TuijianBean.ListBean> list) {
        goodsList.addAll(list);
        adater.notifyDataSetChanged();
    }

    @Override
    public void onFaild(String errorMsg) {
        Log.i("TAG", "onFaild: "+errorMsg);
    }
}
