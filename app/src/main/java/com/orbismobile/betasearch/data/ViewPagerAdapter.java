package com.orbismobile.betasearch.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.orbismobile.betasearch.ui.fragments.AppliesFragment;
import com.orbismobile.betasearch.ui.fragments.MeFragment;
import com.orbismobile.betasearch.ui.fragments.NotifyFragment;
import com.orbismobile.betasearch.ui.fragments.SearchsFragment;

/**
 * Created by tohure on 12/04/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private SearchsFragment searchsFragment;
    private AppliesFragment appliesFragment;
    private NotifyFragment notifyFragment;
    private MeFragment meFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public SearchsFragment getSearchsFragment() {
        if (searchsFragment == null) searchsFragment = new SearchsFragment();
        return searchsFragment;
    }

    public AppliesFragment getAppliesFragment() {
        if (appliesFragment == null) appliesFragment = new AppliesFragment();
        return appliesFragment;
    }

    public NotifyFragment getNotifyFragment() {
        if (notifyFragment == null) notifyFragment = new NotifyFragment();
        return notifyFragment;
    }

    public MeFragment getMeFragment() {
        if (meFragment == null) meFragment = new MeFragment();
        return meFragment;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return getSearchsFragment();
            case 1: return getAppliesFragment();
            case 2: return getNotifyFragment();
            case 3: return getMeFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
