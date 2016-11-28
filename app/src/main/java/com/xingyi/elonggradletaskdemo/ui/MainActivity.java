package com.xingyi.elonggradletaskdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xingyi.elonggradletaskdemo.R;
import com.xingyi.elonggradletaskdemo.widget.SExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SExpandableListView expandableListView;
    private List<CouponEntity> coupons;
    private CouponAdapter couponAdapter;
    private int loadCount;
    private boolean isPull;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (loadCount > 6) {
                expandableListView.refreshComplete();
                expandableListView.setNoMore(true);
            } else {
                addLoadMoreData();
                if (isPull) {
                    expandableListView.refreshComplete();
                }
                couponAdapter.notifyDataSetChanged();
//                expanedAll();
            }
            Log.e("TAG---收到消息:", loadCount + "-->");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        expandableListView = (SExpandableListView) findViewById(R.id.elv_coupon);
        // 在设置适配器之前设置是否支持下拉刷新
        expandableListView.setLoadingMoreEnabled(true);
        expandableListView.setPullRefreshEnabled(true);

        coupons = creatData();
        couponAdapter = new CouponAdapter();
        couponAdapter.setData(coupons);
        expandableListView.setAdapter(couponAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MainActivity.this, "-----点击了组----groupPosition" + groupPosition, Toast.LENGTH_SHORT).show();
                //返回true消耗掉组点击事件,从拦截了折叠的事件,
                return false;
//                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this, "-----点击了组----groupPosition" + groupPosition + "---孩子--childPosition" + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//        expanedAll();
        expandableListView.setmLoadingListener(new SExpandableListView.LoadingListener() {
            @Override
            public void onLoadMore() {
                isPull = false;
                loadCount++;
                Message msg = handler.obtainMessage();
                msg.arg1 = loadCount;
                handler.sendMessageDelayed(msg, 2000);
                Log.e("TAG---HANDLER:", loadCount + "-->");
            }

            @Override
            public void onRefresh() {
                isPull = true;
                loadCount++;
                Message msg = handler.obtainMessage();
                msg.arg1 = loadCount;
                handler.sendMessageDelayed(msg, 2000);
                Log.e("TAG---HANDLER:", loadCount + "-->");
            }
        });

    }

    /**
     * 全部展开,
     */
    private void expanedAll() {
        for (int i = 0; i < coupons.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    /**
     * 模拟加载更多数据
     */
    private void addLoadMoreData() {
        CouponEntity temp = new CouponEntity();
        temp.setGroupTitle("分页加载-->第" + loadCount + "--次数");
        List<CouponChildEntity> childEntities = new ArrayList<>();
        for (int j = 0; j < 2; j++) {
            CouponChildEntity couponChildEntity = new CouponChildEntity();
            couponChildEntity.setChildTitle("分页加载--孩子第" + loadCount + "次子ID" + j);
            childEntities.add(couponChildEntity);
            temp.setOneGroupData(childEntities);
        }
        if (isPull) {
            coupons.add(0, temp);
        } else {
            coupons.add(temp);
        }
    }

    /**
     * 创建出事的模拟数据
     *
     * @return
     */
    private List<CouponEntity> creatData() {
        coupons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CouponEntity temp = new CouponEntity();
            temp.setGroupTitle("优惠券组-->index:" + i);
            List<CouponChildEntity> childEntities = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                CouponChildEntity couponChildEntity = new CouponChildEntity();
                couponChildEntity.setChildTitle("满减优惠组ID" + i + "子ID" + j);
                childEntities.add(couponChildEntity);
                temp.setOneGroupData(childEntities);
            }
            coupons.add(temp);
        }
        return coupons;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class CouponAdapter extends BaseExpandableListAdapter {
        private List<CouponEntity> data;

        public List<CouponEntity> getData() {
            return data;
        }

        public void setData(List<CouponEntity> data) {
            this.data = data;
        }

        @Override
        public int getGroupCount() {
            return data.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return data.get(groupPosition).getOneGroupData().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return data.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return data.get(groupPosition).getOneGroupData().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder = null;
            CouponEntity couponEntity = data.get(groupPosition);
            if (convertView == null) {
                groupViewHolder = new GroupViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_group_title, parent, false);
                groupViewHolder.groupTitle = (TextView) convertView.findViewById(R.id.tv_grounp_title);
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.groupTitle.setText(couponEntity.getGroupTitle());
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder = null;
            CouponChildEntity couponChildEntity = data.get(groupPosition).getOneGroupData().get(childPosition);
            if (convertView == null) {
                childViewHolder = new ChildViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_group_child, parent, false);
                childViewHolder.childTitle = (TextView) convertView.findViewById(R.id.tv_group_child_title);
                convertView.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            childViewHolder.childTitle.setText(couponChildEntity.childTitle);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    static class GroupViewHolder {
        TextView groupTitle;
    }

    static class ChildViewHolder {
        TextView childTitle;
    }

    class CouponEntity {
        private String groupTitle;
        private List<CouponChildEntity> oneGroupData;

        public String getGroupTitle() {
            return groupTitle;
        }

        public void setGroupTitle(String groupTitle) {
            this.groupTitle = groupTitle;
        }

        public List<CouponChildEntity> getOneGroupData() {
            return oneGroupData;
        }

        public void setOneGroupData(List<CouponChildEntity> oneGroupData) {
            this.oneGroupData = oneGroupData;
        }
    }

    class CouponChildEntity {
        private String childTitle;

        public String getChildTitle() {
            return childTitle;
        }

        public void setChildTitle(String childTitle) {
            this.childTitle = childTitle;
        }
    }


}
