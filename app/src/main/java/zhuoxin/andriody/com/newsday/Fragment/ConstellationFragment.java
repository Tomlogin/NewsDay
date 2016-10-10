package zhuoxin.andriody.com.newsday.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuoxin.andriody.com.newsday.Activity.MyApplication;
import zhuoxin.andriody.com.newsday.Activity.WebActivity;
import zhuoxin.andriody.com.newsday.Adapter.ConstellationRecyclerViewAdapter;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.info.ConstellationInfo;
import zhuoxin.andriody.com.newsday.info.Newsinfo;
import zhuoxin.andriody.com.newsday.volleydata.VolleyData;

/**
 * 星座
 * Created by Administrator on 9/14 0014.
 */
public class ConstellationFragment extends Fragment {
    private List<ConstellationInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list;
    private RequestQueue requestQueue;
    private Gson gson;
    private ConstellationInfo info;
    private boolean isfrist = true;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ConstellationRecyclerViewAdapter adapter;
    private String httpUrl = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
    private String httpArg = "&channelName=%E5%9B%BD%E5%86%85%E6%9C%80%E6%96%B0&title=%E4%B8%8A%E5%B8%82&page=";
    private String channelId = "?channelId=5572a109b3cdc86cf39001db";
    private String url = "";
    private String title = "";
    private int lastVisibleItem;
    private LinearLayoutManager manger;
    private TextView textView;
    private int nowpage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.constellationfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        //接收传过来的title
        title = this.getArguments().getString("title");
        Log.e("test", title.toString());
        getcchannelId();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);
        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                getStringReqest(Addurl(nowpage));

            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.ConstellationFragment_recyclerview);
        textView = (TextView) view.findViewById(R.id.recyclerview_tv);
        manger = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manger);
        adapter = new ConstellationRecyclerViewAdapter(getStringReqest(Addurl(nowpage)));
        recyclerView.setAdapter(adapter);
        adapter.setMyitemlistener(new ConstellationRecyclerViewAdapter.Myitemlistener() {
            @Override
            public void onitemclick(int p) {
                String title = list.get(p).getTitle();
                String s = list.get(p).getLink();
                Intent intent = new Intent(getContext(), WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("url", s);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
//上拉加载
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0://滑动停止时如果是最后一个的时候先将isfrst变成false，第二次进来的时候就会执行esle
                        if (lastVisibleItem + 1 == adapter.getItemCount()) {
                            if (isfrist) {
                                isfrist = false;
                            } else {
                                textView.setVisibility(View.VISIBLE);
                                nowpage++;
                                getStringReqest(Addurl(nowpage));
                            }
                        } else { // 如果不是最后一个就将isfrst变成ture,就会执行里面放if
                            isfrist = true;
                        }

                        break;
                    case 1://开始滑动
                        break;
                    case 2://滑动中
                        break;


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manger.findLastVisibleItemPosition();
            }
        });
//        textView.setVisibility(View.GONE);

    }

    private String Addurl(int page) {
        url = httpUrl + channelId + httpArg + page;
        return url;
    }

    //接收不同channelId
    private void getcchannelId() {
        switch (title) {
            case "房产":
                channelId = "?channelId=5572a108b3cdc86cf39001d2";
                break;
            case "社会":
                channelId = "?channelId=5572a10bb3cdc86cf39001f8";
                break;
            case "汽车":
                channelId = "?channelId=5572a109b3cdc86cf39001e5";
                break;
            case "热点":
                channelId = "?channelId=5572a108b3cdc86cf39001cd";
                break;
            case "财经":
                channelId = "?channelId=5572a108b3cdc86cf39001d0";
                break;
            case "科技":
                channelId = "?channelId=5572a10ab3cdc86cf39001f4";
                break;
            case "影视":
                channelId = "?channelId=5572a10ab3cdc86cf39001ec";
                break;
            case "女人":
                channelId = "?channelId=5572a10ab3cdc86cf39001f0";
                break;
        }
    }

    /**
     * 解析并获取数据源
     *
     * @param url
     * @return
     */
    public List<ConstellationInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getStringReqest(String url) {

        requestQueue = MyApplication.getRequestQueue();
        gson = new Gson();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                info = gson.fromJson(s, ConstellationInfo.class);
                list.addAll(info.getShowapi_res_body().getPagebean().getContentlist());
                Log.e("@@@", info.toString());
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                textView.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ConstellationFragment.this.getActivity(), "加载失败", Toast.LENGTH_SHORT).show();
                textView.setVisibility(View.GONE);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("apikey", "03db9b5b9d8c25675268bd00f52b65f4");
                return map;
            }
        };
        request.setTag(url);
        requestQueue.add(request);
        return list;
    }

}
