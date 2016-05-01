package com.sample.walmartpickup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sample.walmartpickup.adapters.DashboardItemsAdapter;
import com.sample.walmartpickup.controls.AutoScrollViewPager;
import com.sample.walmartpickup.fragments.DealsFragment;

/**
 * Created by @author on 4/28/2016.
 */
public class DashBoardActivity extends WalmartPickupBaseActivity {

    private LinearLayout llDashBoard;
    private AutoScrollViewPager viewPagerDashBoard;
    private RecyclerView rvItems;
    private int imageArray[] = {R.drawable.deal_one, R.drawable.deal_two, R.drawable.deal_three};
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    void onCreateSubView() {
        llDashBoard = (LinearLayout) layoutInflater.inflate(R.layout.activity_dashboard, null);
        llContent.addView(llDashBoard, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        initializeViews();
    }

    private void initializeViews() {
        viewPagerDashBoard = (AutoScrollViewPager) llDashBoard.findViewById(R.id.viewPagerDashBoard);
        rvItems = (RecyclerView) llDashBoard.findViewById(R.id.rvItems);
        viewPagerDashBoard.setAdapter(new DealsPagerAdapter(getSupportFragmentManager()));
        viewPagerDashBoard.startAutoScrollPager(viewPagerDashBoard);
        rvItems.setLayoutManager(new LinearLayoutManager(DashBoardActivity.this));
        dashboardItemsAdapter = new DashboardItemsAdapter();
        rvItems.setAdapter(dashboardItemsAdapter);
    }

    public class DealsPagerAdapter extends FragmentStatePagerAdapter {

        public DealsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return new DealsFragment(imageArray[i]);
        }

        @Override
        public int getCount() {
            return imageArray.length;
        }
    }

}
