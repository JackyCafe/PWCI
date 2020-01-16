package tw.com.ian.pwci.Adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import tw.com.ian.pwci.Fragments.ConsultFragment;
import tw.com.ian.pwci.Fragments.MedicationNoticeFragment;
import tw.com.ian.pwci.Fragments.MedicationRecordFragment;
import tw.com.ian.pwci.Fragments.MedicationStyleFragment;

public class ViewPagerAdapter extends FragmentStateAdapter
{
    private int count;
    private Context context;
    private TabLayout tabLayout;
    List<Fragment> allFragment = new ArrayList<Fragment>();
    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        ConsultFragment consultFragment = new ConsultFragment(); //慢簽回診
        MedicationStyleFragment medicationStyleFragment = new MedicationStyleFragment(); //用藥須知
        MedicationNoticeFragment medicationNoticeFragment = new MedicationNoticeFragment(); //用藥提醒
        MedicationRecordFragment medicationRecordFragment = new MedicationRecordFragment(); //用藥紀錄

        allFragment.add(consultFragment);
        allFragment.add(medicationStyleFragment);
        allFragment.add(medicationNoticeFragment);
        allFragment.add(medicationRecordFragment);
     }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return  allFragment.get(position);
    }

    @Override
    public int getItemCount() {
         return allFragment.size();
    }
}
