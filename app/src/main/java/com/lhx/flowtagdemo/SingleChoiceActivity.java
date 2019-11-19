package com.lhx.flowtagdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.lhx.flowtagdemo.adapter.SingleRecyclerAdapter;
import com.lhx.flowtagdemo.bean.TestBean;
import com.lhx.flowtagdemo.recycler.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleChoiceActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<TestBean> mListData;
    private SingleRecyclerAdapter mAdapter;

    public static Set<Integer> positionSet = new HashSet<>();
    private boolean selectMode = false; //选择模式 多选或者单选  true  多选


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        initRecyclerView();

        initData();

        initListener();

    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(manager);

    }

    private void initData() {
        mListData = new ArrayList<>();
        mAdapter = new SingleRecyclerAdapter(SingleChoiceActivity.this, mListData);
        mRecyclerView.setAdapter(mAdapter);

        mListData.add(new TestBean("1", "历史"));
        mListData.add(new TestBean("2", "人工智能"));
        mListData.add(new TestBean("3", "基础算法"));
        mListData.add(new TestBean("4", "语言数字基础"));
        mListData.add(new TestBean("5", "大数据应用"));
        mListData.add(new TestBean("6", "数据分析"));
        mListData.add(new TestBean("7", "政治"));
        mListData.add(new TestBean("8", "新媒体入门"));
        mListData.add(new TestBean("9", "JAVA"));
        mListData.add(new TestBean("10", "C"));
        mListData.add(new TestBean("11", "PHP"));
        mAdapter.update(mListData);

    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (selectMode) {
                    // 如果当前处于多选状态，则进入多选状态的逻辑
                    // 维护当前已选的position
                    addOrRemove(position);
                } else {
                    // 如果不是多选状态，则进入单选事件的业务逻辑
                    if (!positionSet.contains(position)) {
                        // 选择不同的单位时取消之前选中的单位
                        positionSet.clear();
                    }
                    addOrRemove(position);
                }

                String kindName = mListData.get(position).getTagName();
                Toast.makeText(SingleChoiceActivity.this, kindName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });

    }

    private void addOrRemove(int position) {
        if (positionSet.contains(position)) {
            // 如果包含，则撤销选择
            positionSet.remove(position);
        } else {
            // 如果不包含，则添加
            positionSet.add(position);
        }
        if (positionSet.size() == 0) {
            // 如果没有选中任何的item，则退出多选模式
            mAdapter.notifyDataSetChanged();
            selectMode = false;
        } else {
            // 更新列表界面，否则无法显示已选的item
            mAdapter.notifyDataSetChanged();
        }
    }
}
