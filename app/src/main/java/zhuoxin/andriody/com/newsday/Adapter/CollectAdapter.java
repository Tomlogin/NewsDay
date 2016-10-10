package zhuoxin.andriody.com.newsday.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.info.WebInfo;

/**
 * Created by Administrator on 9/20 0020.
 */
public class CollectAdapter extends BaseAdapter {
    private List<WebInfo> list;
    private LayoutInflater inflater;
    private Context context;
    public CollectAdapter(List<WebInfo> list ,Context context) {
        this.list = list;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.collect_listviewitem,null);
        TextView title=(TextView) convertView.findViewById(R.id.collect_lvitem_title);
        title.setText(list.get(position).title);
        TextView url=(TextView) convertView.findViewById(R.id.collect_lvitem_url);
        url.setText(list.get(position).url);
        return convertView;
    }

}
