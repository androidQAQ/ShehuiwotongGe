package com.bw.csh_item.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.bw.csh_item.R;
import com.bw.csh_item.adapter.MySeekListAdapter;
import com.bw.csh_item.api.MyApi;
import com.bw.csh_item.bean.SeekListBean;
import com.bwei.okhttp3ps.utils.GsonObjectCallback;
import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.example.searchview_library.SearchALG;
import com.example.searchview_library.SearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SouActivity extends BaseActivity {

    private SearchView searchView;
    private SearchALG searchALG;
    private RecyclerView recycler_seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        //找控件
        searchView = (SearchView) findViewById(R.id.searchView);
        recycler_seek= (RecyclerView) findViewById(R.id.recycler_seek);//找到recycview

        initData();

        searchView.setOnSearchListener(new MyOnSearchListener());
    }

    private List<String> changedHintDatas;

    //搜索完成后进行点击进入下单

    /**
     * 设置searview的监听
     */
    class MyOnSearchListener implements SearchView.OnSearchListener {

        /**
         * 搜索回调
         * @param searchText 进行搜索的文本
         */
        @Override
        public void onSearch(String searchText) {
            if (!TextUtils.isEmpty(searchText)) {


                Toast.makeText(SouActivity.this, "完成搜索" + searchText, Toast.LENGTH_SHORT).show();

                //設置GridLout
                recycler_seek.setLayoutManager(new GridLayoutManager(SouActivity.this,2));

                //搜索劳力士后展示请求到数据
               OkHttp3Utils.doGet(MyApi.SeekList, new GsonObjectCallback<SeekListBean>() {
                   @Override
                   public void onUi(SeekListBean seekListBean) {
                       final List<SeekListBean.DatasBean.GoodsListBean> goods_list = seekListBean.getDatas().getGoods_list();
                       MySeekListAdapter adapter = new MySeekListAdapter(goods_list,SouActivity.this);
                       recycler_seek.setAdapter(adapter);


                       adapter.setOnItemClickListener(new MySeekListAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(SouActivity.this, XiangqingActivity.class);
                                intent.putExtra("name",goods_list.get(position).getGoods_name());
                                intent.putExtra("price",goods_list.get(position).getGoods_price());
                                intent.putExtra("img",goods_list.get(position).getGoods_image_url());
                                intent.putExtra("good_id",goods_list.get(position).getGoods_id());
                                startActivity(intent);
                            }
                        });
                   }
                   @Override
                   public void onFailed(Call call, IOException e) {

                   }
               });
            } else {
                Toast.makeText(SouActivity.this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        }
        /**
         * 刷新提示列表
         * @param changedText 改变后的文本
         */
        @Override
        public void onRefreshHintList(String changedText) {
            if (changedHintDatas == null) {
                changedHintDatas = new ArrayList<>();
            } else {
                changedHintDatas.clear();
            }
            if (TextUtils.isEmpty(changedText)) {
                return;
            }
            for (int i = 0; i < hint_datas.size(); i++) {
                String hint_data = hint_datas.get(i);
                boolean isAdd = searchALG.isAddToHintList(hint_data, changedText);
                if (isAdd) {
                    changedHintDatas.add(hint_datas.get(i));
                }
            }

            /**
             * 根据搜索框文本的变化，动态的改变提示的listView
             */
            searchView.updateHintList(changedHintDatas);
        }
    }
    //热搜数据
    private List<String> hot_datas;
    //提示列表数据
    private List<String> hint_datas;

    private void initData() {
        hot_datas = new ArrayList<>();
        hint_datas = new ArrayList<>();

        searchALG = new SearchALG(this);

        for (int i = 0; i < 10; i++) {
            hot_datas.add("Android Hot " + i);
        }

        //设置热搜数据显示的列数
        searchView.setHotNumColumns(2);
        //设置热搜数据
        searchView.setHotSearchDatas(hot_datas);

        /**
         * 设置提示数据的集合
         */
        for (int i = 0; i < 10; i++) {
            hint_datas.add("ts"+"安卓学习" + "Android Hint " + i + " 你好");
        }

        /**
         * 设置自动保存搜索记录
         */
        searchView.keepSearchHistory(true);

        //设置提示列表的最大显示列数
        searchView.setMaxHintLines(8);
        //设置保存搜索记录的个数
        searchView.setMaxHistoryRecordCount(6);


    }
}
