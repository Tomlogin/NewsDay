package zhuoxin.andriody.com.newsday.Adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.info.ConstellationInfo;
import zhuoxin.andriody.com.newsday.info.Newsinfo;

/**
 * Created by Administrator on 9/14 0014.
 */
public class ConstellationRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ConstellationInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean>list;
    private DisplayImageOptions options;
    public interface Myitemlistener{
        void onitemclick(int p);
    }
    private Myitemlistener myitemlistener=null;

    public void setMyitemlistener(Myitemlistener myitemlistener) {
        this.myitemlistener = myitemlistener;
    }

    public ConstellationRecyclerViewAdapter(List<ConstellationInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean>list) {
        this.list = list;
        options=new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.img_news_lodinglose)
                .showImageOnFail(R.drawable.img_news_lodinglose)
                .showImageOnLoading(R.drawable.img_news_loding)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.constellationrecycler_item,parent,false);
        itemRecyclerHolder holder=new itemRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        itemRecyclerHolder itemholder=(itemRecyclerHolder)holder;
        itemholder.context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myitemlistener.onitemclick(position);
            }
        });
        itemholder.titlte.setText(list.get(position).getTitle());
        itemholder.context.setText(list.get(position).getPubDate());
 //       itemholder.img.setImageResource(R.drawable.a111);
        List<ConstellationInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageurlsBean> imageurlsBean=
                list.get(position).getImageurls();
        if(imageurlsBean.size()!=0){
            ImageLoader.getInstance().displayImage(imageurlsBean.get(0).getUrl(),itemholder.img);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class itemRecyclerHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView titlte,context;
        public itemRecyclerHolder(View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.Constellationitem_img);
            titlte=(TextView) itemView.findViewById(R.id.Constellationitem_title);
            context=(TextView) itemView.findViewById(R.id.Constellationitem_context);
        }

        @Override
        public String toString() {
            return "itemRecyclerHolder{" +
                    "img=" + img +
                    ", titlte=" + titlte +
                    ", context=" + context +
                    '}';
        }
    }
}
