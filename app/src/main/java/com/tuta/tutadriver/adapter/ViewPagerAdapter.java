package com.tuta.tutadriver.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private final List<Integer> mFragmentUniqueId = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }




    public void addFragment(Fragment fragment, String title ) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void addFragment(Fragment fragment, String title , int uniqueId ) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        mFragmentUniqueId.add(uniqueId);
    }


    public void removeFragment(int pos){
        mFragmentList.remove(pos);
        notifyDataSetChanged();
    }

    public void removeFragment(Fragment fragment){
        mFragmentList.remove(fragment);
        notifyDataSetChanged();
    }

    public boolean hasFragment(Fragment fragment){
        if(mFragmentList.contains(fragment)) {
            return true;
        } else {
            return false;
        }
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }

    public String getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
