package tw.com.ian.pwci.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Adapter.ViewPagerAdapter;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Util.Initializer;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetupFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    String[] tabs = {"常用設定","常用醫師設定"};
    Initializer app;

    public SetupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setup, container, false);
        viewPager = v.findViewById(R.id.viewpager_setup);
        tabLayout = v.findViewById(R.id.tabs_setup);
        viewPager.setAdapter(createAdapter());

        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabs[position]);
                    }
                }).attach();

       return v;
    }

    private ViewPagerAdapter createAdapter() {
        List<Fragment> allFragment = new ArrayList<>();
        allFragment.add(GeneralSetupFragment.newInstance());//通用設定
        allFragment.add(DepartmentFragment.newInstance()); //常用科別

        ViewPagerAdapter adapter = new ViewPagerAdapter(this,allFragment);

        return adapter;
    }


}
