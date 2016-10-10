package zhuoxin.andriody.com.newsday.volleydata;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhuoxin.andriody.com.newsday.Activity.MyApplication;
import zhuoxin.andriody.com.newsday.Adapter.NewsFragmentAdapter;
import zhuoxin.andriody.com.newsday.info.ConstellationInfo;
import zhuoxin.andriody.com.newsday.info.Title;

/**
 * Created by Administrator on 9/14 0014.
 */
public class VolleyData {
    private List<ConstellationInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list;
    private RequestQueue requestQueue;
    private Gson gson;
    private ConstellationInfo info;
    private NewsFragmentAdapter adapter;
    public List<ConstellationInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getStringReqest(String url){
        list=new ArrayList<>();
        requestQueue= MyApplication.getRequestQueue();
        gson=new Gson();
        StringRequest request=new StringRequest(Request.Method.GET,url,new Response.Listener<String>(){

            @Override
            public void onResponse(String s) {
                info=gson.fromJson(s,ConstellationInfo.class);
                list.addAll(info.getShowapi_res_body().getPagebean().getContentlist());
               adapter.notifyDataSetChanged();

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("apikey", "03db9b5b9d8c25675268bd00f52b65f4");
                return map;
            }
        };
        request.setTag(url);
        requestQueue.add(request);
        Log.e("testdata",list.size()+"");
return list;
    }
}
