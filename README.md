# ElongGradleTaskDemo
支持下拉刷新和加载更多的ExpanableListview


#预览:支持下拉刷新和加载更多的ExpandableListView


----------
![这里写图片描述](http://img.blog.csdn.net/20161128192243437)

###模拟器有点卡,滑动的时候鼠标不方便


----------


###怎么用:
 

 1. XML中声明

```
  <com.xingyi.elonggradletaskdemo.widget.SExpandableListView
        android:listSelector="@android:color/transparent"
        android:id="@+id/elv_coupon"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:dividerHeight="1dp">

    </com.xingyi.elonggradletaskdemo.widget.SExpandableListView>
```

 2. UI中配置下拉刷新的回调以及是否支持下拉和加载更多
  

```

        expandableListView = (SExpandableListView)           findViewById(R.id.elv_coupon);
        // 在设置适配器之前设置是否支持下拉刷新
        expandableListView.setLoadingMoreEnabled(true);
        expandableListView.setPullRefreshEnabled(true);
        expandableListView.setAdapter(couponAdapter);
```

 3. 设置下拉刷新和加载跟多的回调接口
  

```
        expandableListView.setmLoadingListener(new SExpandableListView.LoadingListener() {
            @Override
            public void onLoadMore() {
	            // 模拟加载更多
                isPull = false;
                // 这里应该在项目中请求,这里用延时模拟接口请求
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
                // 模拟接口请求下拉刷新
                Message msg = handler.obtainMessage();
                msg.arg1 = loadCount;
                handler.sendMessageDelayed(msg, 2000);
                Log.e("TAG---HANDLER:", loadCount + "-->");
            }
        });



     private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 模拟接口回复数据,加入下拉刷新次数为6次的话我们就认为没有更多了
            if (loadCount > 6) {
                expandableListView.refreshComplete();
                expandableListView.setNoMore(true);
            } else {
                addLoadMoreData();
                if (isPull) {
                    expandableListView.refreshComplete();
                }
                couponAdapter.notifyDataSetChanged();
                // 是不是全部展开,根据需求设置
//                expanedAll();
            }
            Log.e("TAG---收到消息:", loadCount + "-->");

        }
    };

```


----------

###简单看下实现过程

 1. 自定义ExpandableListview
 2. 重写onTouch事件 
 3. 给自定义的view添加滑动监听事件
 4. 初始化的时候给View添加header 和 footer 用来下拉刷新

```
// 这里在自定view构造函数中调用的增加header和footer的代码,注意下两种添加方式的区别,详情见注释和源代码
  private void initSE(Context context) {
        /**
         * 这里是footer的填充,注意指定他的父亲为当前的listview,
         * 这里footer不用指定layoutparem是因为footer 在填充的时候已经指定了他的父view
         */
        loadMoreView = LayoutInflater.from(context).inflate(R.layout.item_footer_view, this, false);
        loadMorePb = (ProgressBar) loadMoreView.findViewById(R.id.pb_loading);
        loadMoreDesc = (TextView) loadMoreView.findViewById(R.id.tv_loadmore_desc);
    }

    @Override
    public void setAdapter(ExpandableListAdapter adapter) {
        if (pullRefreshEnabled) {
            mRefreshHeader = new ArrowRefreshHeader(getContext());
            mRefreshHeader.setProgressStyle(ProgressStyle.LineScale);
            /**
             * 注意一定要指定header的layoutparasm为 AbsListView,以为 header是 new出来的他默认的 layoutparm是当前header的类型,
             * 如果我们不修改的话会报错 类型转化异常,
             */
            mRefreshHeader.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
            addHeaderView(mRefreshHeader);
        }
        if (loadingMoreEnabled) {
            addFooterView(loadMoreView);
        }
        super.setAdapter(adapter);
    }
```

 


#源代码下载地址:[https://github.com/GuoFeilong/ElongGradleTaskDemo谢谢star](https://github.com/GuoFeilong/ElongGradleTaskDemo)




 
