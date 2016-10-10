package zhuoxin.andriody.com.newsday.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 8/31 0031.
 */
public class MainViewpagerAdapter extends PagerAdapter{
    private List<View> viewList;
    private List<String> titles;
   public MainViewpagerAdapter( List<View> viewList,List<String> titles){
       this.viewList=viewList;
       this.titles=titles;
   }
    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position),0);
        return viewList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
