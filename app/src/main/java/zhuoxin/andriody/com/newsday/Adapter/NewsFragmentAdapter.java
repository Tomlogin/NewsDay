package zhuoxin.andriody.com.newsday.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


/**
 * Created by Administrator on 9/1 0001.
 */
public class NewsFragmentAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragments;
    private List<String> tiltes;
    public NewsFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tiltes) {
        super(fm);
        this.fragments=fragments;
        this.tiltes=tiltes;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tiltes.get(position);
    }
}
