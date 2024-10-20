package com.sap.rhythmhaven.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sap.rhythmhaven.LoginTabFragment;
import com.sap.rhythmhaven.signupTabFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return new signupTabFragment();
        }
        return new LoginTabFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
