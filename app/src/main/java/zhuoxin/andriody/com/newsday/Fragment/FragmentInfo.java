package zhuoxin.andriody.com.newsday.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import zhuoxin.andriody.com.newsday.Activity.AddActivity;
import zhuoxin.andriody.com.newsday.Activity.TAddactivity;
import zhuoxin.andriody.com.newsday.Adapter.NewsFragmentAdapter;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Sharepre;

public class FragmentInfo extends Fragment implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private List<String> titles;
    private NewsFragmentAdapter adapter;
    private View view;
    private ImageView add;
    private  ConstellationFragment constellationFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        setcontentLayout();
        initView();
        viewPager.setOffscreenPageLimit(0);
        tabLayout.setupWithViewPager(viewPager);

    }


    public void setcontentLayout() {
        viewPager = (ViewPager) view.findViewById(R.id.fragmentinfo_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.main_tablayout);
        add = (ImageView) view.findViewById(R.id.fragmentinfotitle_img);
        add.setOnClickListener(this);
//        String [] title={"互联网","安卓","热点","四川","星座","军事"};
//        for (int i = 0; i <title.length ; i++) {
//            titles.add(title[i]);
//        }

    }


    public void initView() {
        fragmentList = new ArrayList<>();
        if(titles!=null){
            titles.clear();
        }
        fragmentList.clear();
        titles = Sharepre.gettitlesListData(getContext());
        for (int i = 0; i < titles.size(); i++) {
            constellationFragment=new ConstellationFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title",titles.get(i));
            constellationFragment.setArguments(bundle);
            fragmentList.add(constellationFragment);
//            if(titles.get(i).equals("星座")){
//                Log.e("test","执行for"+titles.get(i) );
//                ConstellationFragment constellationFragment=new ConstellationFragment();
//                fragmentList.add(constellationFragment);
//            }else if(titles.get(i).equals("热点")){
//                TwoFragment twoFragment=new TwoFragment();
//                fragmentList.add(twoFragment);
//            }else {
//            OneFragment fragment = new OneFragment();
//            fragmentList.add(fragment);
//            }
        }
        adapter = new NewsFragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), TAddactivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101 && requestCode == 1000&&data.getExtras().getBoolean("needUpdate")) {
            initView();
            adapter.notifyDataSetChanged();
            tabLayout.setupWithViewPager(viewPager);
//            List<String> data1 = (ArrayList) data.getExtras().getSerializable("1");
//            fragmentList = new ArrayList<>();
//            for (int i = 0; i < data1.size(); i++) {
//                OneFragment fragment = new OneFragment();
//                fragmentList.add(fragment);
//                adapter = new NewsFragmentAdapter(getActivity().getSupportFragmentManager(), fragmentList, data1);
//                viewPager.setAdapter(adapter);
//                tabLayout.setupWithViewPager(viewPager);
//
//            }
            }
        }


}