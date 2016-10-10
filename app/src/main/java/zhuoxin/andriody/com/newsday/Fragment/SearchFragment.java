package zhuoxin.andriody.com.newsday.Fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zhuoxin.andriody.com.newsday.Activity.WebActivity;
import zhuoxin.andriody.com.newsday.Adapter.CollectAdapter;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Webutil;
import zhuoxin.andriody.com.newsday.info.WebInfo;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener{
    private EditText editText;
    private ImageView search;
    private ListView listView;
    private List<WebInfo> list=new ArrayList<>();
    private CollectAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText=(EditText)view.findViewById(R.id.edittext);
        listView=(ListView)view.findViewById(R.id.search_lv);
        adapter=new CollectAdapter(list,getContext());
        listView.setAdapter(adapter);
        setViewlistener();
        search=(ImageView)view.findViewById(R.id.icon_seach);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"SB点红线下面啊",Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(this);
    }
    private void setViewlistener(){
        //editText监听
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// TODO: 9/20 0020 改变之前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
// TODO: 9/20 0020 改变中
                String temp=s.toString();
                if(temp.length()>0){
                    list.clear();
                    list.addAll(Webutil.getsearch(getContext(),temp));
                    if(list.size()>0){
                        adapter.notifyDataSetChanged();
                        listView.setVisibility(View.VISIBLE);
                    }
                }else{
                    listView.setVisibility(View.GONE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
// TODO: 9/20 0020 改变之后
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getContext(), WebActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("url",list.get(position).url);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
