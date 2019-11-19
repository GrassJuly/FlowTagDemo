package com.lhx.flowtagdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhx.flowtagdemo.MultipleChoiceActivity;
import com.lhx.flowtagdemo.R;
import com.lhx.flowtagdemo.bean.TestBean;
import com.lhx.flowtagdemo.recycler.CheckableLayout;
import com.lhx.flowtagdemo.recycler.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by LJZ
 * on 2019-11-19
 * Describe:
 */
public class MultipleRecyclerAdapter extends RecyclerView.Adapter<MultipleRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<TestBean> mListData = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MultipleRecyclerAdapter(Context mContext, List<TestBean> mListData) {
      //  mListData = new ArrayList<>();
        this.mContext = mContext;
        this.mListData = mListData;
    }
    public void update(List<TestBean> list){
        if(list != null && list.size() > 0){
            mListData.addAll(list);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView typeTv;
        CheckableLayout rootLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            typeTv = (TextView) itemView.findViewById(R.id.alive_type_tv);
            rootLayout = (CheckableLayout) itemView.findViewById(R.id.root_layout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext == null){
            mContext = viewGroup.getContext();
        }
        View view  = LayoutInflater.from(mContext).inflate(R.layout.item_recycler,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Set<Integer> positionSet = MultipleChoiceActivity.positionSet;
        //检查set里是否包含position，包含则显示选中的背景色，不包含则反之
        if (positionSet.contains(position)) {
            holder.rootLayout.setChecked(true);
            holder.typeTv.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.rootLayout.setChecked(false);
            holder.typeTv.setTextColor(mContext.getResources().getColor(R.color.grey_60));
        }

        TestBean bean = mListData.get(position);
        holder.typeTv.setText(bean.getTagName());
        if(mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(holder.itemView, pos);
                    holder.rootLayout.setChecked(true);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }


}
