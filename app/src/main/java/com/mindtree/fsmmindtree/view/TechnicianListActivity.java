package com.mindtree.fsmmindtree.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;

import java.util.ArrayList;

/**
 * Created by Edwin on 15/02/2015.
 */
public class TechnicianListActivity extends ActionBarActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"List", "Map"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicians);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

        TechnicianListFragment tab1 = new TechnicianListFragment();
        TechnicianMapFragment tab2 = new TechnicianMapFragment();

        ArrayList <Fragment> tabList = new ArrayList<Fragment>();
        tabList.add(tab1);
        tabList.add(tab2);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, tabList);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }
}

