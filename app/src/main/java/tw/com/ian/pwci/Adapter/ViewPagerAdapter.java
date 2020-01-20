package tw.com.ian.pwci.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentStateAdapter
{

    List<Fragment> allFragment = new ArrayList<Fragment>();
    public ViewPagerAdapter(@NonNull Fragment fragment, List<Fragment> allFragment ) {
        super(fragment);
        this.allFragment = allFragment;
     
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
