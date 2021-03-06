package tw.com.ian.pwci.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Adapter.ViewPagerAdapter;
import tw.com.ian.pwci.R;
import tw.com.ian.pwci.Util.Initializer;


public class MedicalFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    String[] tabs = {"主頁","慢籤回診","用藥紀錄"};
    Initializer app;

    public MedicalFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        app = (Initializer) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_medical, container, false);
        viewPager = v.findViewById(R.id.view_pager);
        tabLayout = v.findViewById(R.id.tabs);

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
        allFragment.add(MedicalMainFragment.newInstance());
        allFragment.add(ConsultFragment.newInstance());//慢簽回診
        allFragment.add(MedicationRecordFragment.newInstance()); //用藥紀錄
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,allFragment);
        return adapter;
    }

}
