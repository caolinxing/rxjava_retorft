package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pojo.GoodsBean;
import com.example.view.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;

import java.util.List;

public class XrecyclerAdater extends RecyclerView.Adapter<XrecyclerAdater.ViewHolder> {
    Context context;
    List<GoodsBean.TuijianBean.ListBean> list;

    public XrecyclerAdater(Context context, List<GoodsBean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_layout,null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phoenix.with(holder.img).load(list.get(position).getImages().split("[|]")[0]);
        holder.tv_title.setText(list.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView img;
        private TextView tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.sdv1);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
