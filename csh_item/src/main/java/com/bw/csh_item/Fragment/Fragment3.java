package com.bw.csh_item.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.csh_item.R;
import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.ShopBean;
import com.bw.csh_item.bean.UnregBean;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;



/**
 * Created by 曹少航 on 2017/10/11.
 */

public class Fragment3 extends Fragment implements View.OnClickListener {
    private ExpandableListView expand;
    private CheckBox cb_all;
    private Button shop_count;
    private TextView shop_allmoney;
    private MyExpandAdapter myExpandAdapter;
    private List<ShopBean.DatasBean.CartListBean> cart_list;
    private List<ShopBean.DatasBean.CartListBean.GoodsBean> goodsBeanList ;
    private List<List<ShopBean.DatasBean.CartListBean.GoodsBean>>list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment3,null);
        //找控件
        expand= (ExpandableListView) v.findViewById(R.id.expand);
        shop_count= (Button) v.findViewById(R.id.shop_count);
        cb_all= (CheckBox) v.findViewById(R.id.cb_all);//点击全选
        shop_allmoney= (TextView) v.findViewById(R.id.shop_allmoney);

        cb_all.setOnClickListener(this);
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        getExpandableData();
    }

    /**
     * 展示购物车里面的数据
     */

    private void getExpandableData() {
        Map<String,String> map=new HashMap<>();
        map.put("key", MyApi.sharedPreferences.getString("key",""));
        OkHttp3Utils.doPost(MyApi.ShopLists, map, new GsonObjectCallback<ShopBean>() {
            @Override
            public void onUi(ShopBean shopBean) {
               list=new ArrayList<List<ShopBean.DatasBean.CartListBean.GoodsBean>>();
                cart_list=shopBean.getDatas().getCart_list();
                for(int i=0;i<cart_list.size();i++){
                    goodsBeanList=cart_list.get(i).getGoods();
                    list.add(goodsBeanList);
                }
                myExpandAdapter = new MyExpandAdapter();

                expand.setAdapter(myExpandAdapter);

                for(int i = 0;i<expand.getCount();i++){
                    expand.expandGroup(i);
                }

            }
            @Override
            public void onFailed(Call call, IOException e) {
            }
        });

    }
    //点击事件
    @Override
    public void onClick(View v) {
        if(((CheckBox)v).isChecked()){
            List<ShopBean.DatasBean.CartListBean> cart_list = this.cart_list;
            for (int i = 0; i < cart_list.size(); i++) {
                ShopBean.DatasBean.CartListBean cartListBean = cart_list.get(i);
                cartListBean.setAllCheck(true);
                List<ShopBean.DatasBean.CartListBean.GoodsBean> goods = cart_list.get(i).getGoods();
                for (int j = 0; j < goods.size(); j++) {
                    List<ShopBean.DatasBean.CartListBean.GoodsBean> beanList = cartListBean.getGoods();
                    for (ShopBean.DatasBean.CartListBean.GoodsBean childData : beanList) {
                        childData.setItemCheck(true);
                    }
                }
            }
            //刷新界面
            notifyCheckAdapter();
        }else {
            List<ShopBean.DatasBean.CartListBean> cart_list = this.cart_list;
            for (int i = 0; i < cart_list.size(); i++) {
                ShopBean.DatasBean.CartListBean cartListBean = cart_list.get(i);
                cartListBean.setAllCheck(false);
                List<ShopBean.DatasBean.CartListBean.GoodsBean> goods = cart_list.get(i).getGoods();
                for (int j = 0; j < goods.size(); j++) {
                    List<ShopBean.DatasBean.CartListBean.GoodsBean> beanList = cartListBean.getGoods();
                    for (ShopBean.DatasBean.CartListBean.GoodsBean childData : beanList) {
                        childData.setItemCheck(false);
                    }
                }
            }
            //刷新界面
            notifyCheckAdapter();
        }

    }
    class MyExpandAdapter extends BaseExpandableListAdapter {

        //删除子条目
        public void deleteData(int groupPosition,int childPosition){
            cart_list.get(groupPosition).getGoods().remove(childPosition);
            notifyDataSetChanged();
        }

        //删除组条目
        public void deleteGroup(int groupPosition){
            cart_list.remove(groupPosition);
            notifyDataSetChanged();
        }

        @Override
        public int getGroupCount() {
            return cart_list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if(groupPosition < cart_list.size()){
                return list.get(groupPosition).size();
            }else{
                return 0;
            }
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return list.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //组条目是否有稳定的ID 数据不会改变
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder = null;
            if(convertView == null){
                groupViewHolder = new GroupViewHolder();
                convertView = View.inflate(getActivity(), R.layout.fragment_shop,null);
                groupViewHolder.group_cb = (CheckBox) convertView.findViewById(R.id.group_cb);
                groupViewHolder.group_store_name = (TextView) convertView.findViewById(R.id.group_store_name);
                convertView.setTag(groupViewHolder);
            }else{
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            ShopBean.DatasBean.CartListBean cartListBean = cart_list.get(groupPosition);
            groupViewHolder.group_store_name.setText(cartListBean.getStore_name());
            //一级
            if(cart_list.get(groupPosition).isAllCheck()){
                //被选中则设置为选中状态
                groupViewHolder.group_cb.setChecked(true);
            }else{ //否则设置为未选中
                groupViewHolder.group_cb.setChecked(false);
            }

            //一级监听   将下标和组件传入
            groupViewHolder.group_cb.setOnClickListener(new onGroupClickListener(groupPosition,groupViewHolder.group_cb));
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder = null;
            if(convertView == null){
                childViewHolder = new ChildViewHolder();
                convertView = View.inflate(getActivity(),R.layout.fragment_child,null);
                childViewHolder.child_cb = (CheckBox) convertView.findViewById(R.id.child_cb);
                childViewHolder.child_goods_name = (TextView) convertView.findViewById(R.id.child_goods_name);
                childViewHolder.child_goods_price = (TextView) convertView.findViewById(R.id.child_goods_price);
                childViewHolder.child_store_name = (TextView) convertView.findViewById(R.id.child_store_name);
                childViewHolder.child_goods_num = (TextView) convertView.findViewById(R.id.child_goods_num);
                childViewHolder.child_image_url = (ImageView) convertView.findViewById(R.id.child_image_url);
                childViewHolder.child_delete = (Button) convertView.findViewById(R.id.child_delete);
                convertView.setTag(childViewHolder);
            }else{
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            final ShopBean.DatasBean.CartListBean.GoodsBean goodsBean = list.get(groupPosition).get(childPosition);
            childViewHolder.child_goods_name.setText(goodsBean.getGoods_name());
            childViewHolder.child_store_name.setText(goodsBean.getStore_name());
            childViewHolder.child_goods_num.setText(goodsBean.getGoods_num());
            childViewHolder.child_goods_price.setText(goodsBean.getGoods_price());
          //  ImageLoader.getInstance().displayImage(goodsBean.getGoods_image_url(),childViewHolder.child_image_url, MyApp.getDisplay());
            Picasso.with(getActivity()).load(goodsBean.getGoods_image_url()).into(childViewHolder.child_image_url);
            childViewHolder.child_cb.setChecked(list.get(groupPosition).get(childPosition).isItemCheck());
            childViewHolder.child_cb.setOnClickListener(new onChildCheckListener(groupPosition,childPosition,childViewHolder.child_cb));
            childViewHolder.child_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,String> map = new HashMap<String, String>();
                    map.put("key",MyApi.sharedPreferences.getString("key",""));
                    map.put("cart_id",goodsBean.getCart_id());
                  OkHttp3Utils.doPost( MyApi.DeleteURL,map, new GsonObjectCallback<UnregBean>() {
                        @Override
                        public void onUi(UnregBean unregBean) {
                            if(unregBean.getCode() == 200){
                                Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
                                deleteData(groupPosition,childPosition);
                                if(goodsBeanList.size() == 0){
                                    deleteGroup(groupPosition);
                                }
                            }
                        }

                        @Override
                        public void onFailed(Call call, IOException e) {

                        }
                    });
                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }


        class GroupViewHolder{
            CheckBox group_cb;
            TextView group_store_name;
        }

        class ChildViewHolder{
            Button child_delete;
            CheckBox child_cb;
            ImageView child_image_url;
            TextView child_goods_name,child_store_name,child_goods_price,child_goods_num;
        }

    }
    /**
     * 设置一级监听的类
     */
    public class onGroupClickListener implements View.OnClickListener{

        int groupPosition;
        CheckBox group_cb;

        public onGroupClickListener(int groupPosition, CheckBox group_cb) {
            this.groupPosition = groupPosition;
            this.group_cb = group_cb;
        }

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()){
                //一级全选
                setCheck(true);
            }else{
                setCheck(false);
                cb_all.setChecked(false);
            }
            notifyCheckAdapter();
        }

        //设置选中
        public void setCheck(boolean checkFlag){
            //获取集合中每一条数据
            ShopBean.DatasBean.CartListBean cartListBean = cart_list.get(groupPosition);
            //一级状态
            cartListBean.setAllCheck(checkFlag);
            //全选状态判断
            int num = 0;
            for (int i = 0;i<cart_list.size();i++){
                boolean allCheck = cart_list.get(i).isAllCheck();
                if(!allCheck){
                    num ++;
                }
            }
            if(num == 0){
                cb_all.setChecked(true);
            }else{
                cb_all.setChecked(false);
            }

            //二级状态
            List<ShopBean.DatasBean.CartListBean.GoodsBean> goods = cart_list.get(groupPosition).getGoods();
            for(ShopBean.DatasBean.CartListBean.GoodsBean goodsbean:goods){
                goodsbean.setItemCheck(checkFlag);
            }
        }

    }
    /**
     * 二级监听
     */
    public class onChildCheckListener implements View.OnClickListener{
        int groupPosition;
        int childPosition;
        CheckBox cb_child;

        public onChildCheckListener(int groupPosition, int childPosition, CheckBox cb_child) {
            this.cb_child = cb_child;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()){
                //子选中
                list.get(groupPosition).get(childPosition).setItemCheck(true);
            }else{
                //子未选中
                list.get(groupPosition).get(childPosition).setItemCheck(false);
            }

            //二级联动一级状态
            setParentCheckFlag();

            //检测状态  二级全联选中
            int num = 0;
            for(int i = 0;i<cart_list.size();i++){
                boolean allCheck = cart_list.get(i).isAllCheck();
                if (!allCheck) {
                    num++;
                }
            }
            if (num == 0) {
                cb_all.setChecked(true);
            } else {
                cb_all.setChecked(false);
            }
        }

        //二级联动一级状态
        private void setParentCheckFlag(){
            ShopBean.DatasBean.CartListBean cartListBean = cart_list.get(groupPosition);
            List<ShopBean.DatasBean.CartListBean.GoodsBean> goods = cartListBean.getGoods();
            for (int i = 0; i < goods.size(); i++) {
                if (!goods.get(i).isItemCheck()) {
                    //子未选中 父取消选中
                    cartListBean.setAllCheck(false);
                    notifyCheckAdapter();
                    return;
                }
                if (i == goods.size() - 1) {
                    //子选中 父选中
                    cartListBean.setAllCheck(true);
                    notifyCheckAdapter();
                    return;
                }
            }
            // 没出现全选或者取消全选的时候执行的
            sum();
        }

    }
    //统计数量和价格
    private void sum() {
        int num = 0;
        double price = 0;
        List<ShopBean.DatasBean.CartListBean> cart_list = this.cart_list;
        for (ShopBean.DatasBean.CartListBean parentData : cart_list) {
            for (ShopBean.DatasBean.CartListBean.GoodsBean child : parentData.getGoods()) {
                if (child.isItemCheck()) {
                    num++;
                    double i = Double.parseDouble(child.getGoods_price());

                    price += i;
                }
            }
        }
        shop_count.setText("结算(" + num + ")");
        shop_allmoney.setText("¥" + price);
    }

    //刷新适配器界面
    private void notifyCheckAdapter() {
        sum();
        expand.setAdapter(myExpandAdapter);
        int count = expand.getCount();
        for (int i = 0; i < count; i++) {
            expand.expandGroup(i);
        }
    }


}
