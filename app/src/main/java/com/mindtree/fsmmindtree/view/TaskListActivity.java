package com.mindtree.fsmmindtree.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.BaseActivity;
import com.mindtree.fsmmindtree.presenter.TaskPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Edwin on 15/02/2015.
 */
public class TaskListActivity extends BaseActivity {
    // Declaring Your View and Variables
    CharSequence Titles[] = {"List", "Map"};
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    SlidingTabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.imgFilter)
    ImageView imgFilter;

    private ViewPagerAdapter adapter;

    private EventBus bus = EventBus.getDefault();

    private TaskPresenter mTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicians);
        ButterKnife.bind(this);

        mTaskPresenter = new TaskPresenter(this);

        // Register as a subscriber
        bus.register(this);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        setSupportActionBar(toolbar);
        txtTitle.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
        txtTitle.setText("TASKS");

        TaskListFragment tab1 = new TaskListFragment();
        TaskMapFragment tab2 = new TaskMapFragment();

        ArrayList<Fragment> tabList = new ArrayList<Fragment>();
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

        //  Filter
        imgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFiltersDialog();
            }
        });
    }

    public void onEvent(Integer taskCount) {
        txtTitle.setText("TASKS" + " (" + String.valueOf(taskCount) + ")");
    }

    private void showFiltersDialog() {
        ArrayList <String> options = new ArrayList<String>();
        options.add("Date");
        options.add("Type");
        options.add("Priority");
        options.add("Status");

        final CustomOptionsDialog customOptionsDialog = new CustomOptionsDialog(this);
        customOptionsDialog.setTitle("Sort By");
        customOptionsDialog.setOptions(options);

        //  Get sort type
        int sortType = AppUtils.getIntSetting(this, AppConstants.SORT_KEY);
        if (sortType == -1) {
            sortType = AppConstants.SORT_DATE;
        }
        customOptionsDialog.setOption(sortType);

        customOptionsDialog.setOption1Listener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   mTaskPresenter.sortByDate();

                    //  Save preference
                    AppUtils.saveIntSetting(TaskListActivity.this, AppConstants.SORT_KEY, AppConstants.SORT_DATE);
                }

                customOptionsDialog.hideDialog();
            }
        });

        customOptionsDialog.setOption2Listener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTaskPresenter.sortByType();

                    //  Save preference
                    AppUtils.saveIntSetting(TaskListActivity.this, AppConstants.SORT_KEY, AppConstants.SORT_TYPE);
                }

                customOptionsDialog.hideDialog();
            }
        });

        customOptionsDialog.setOption3Listener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTaskPresenter.sortByPriority();

                    //  Save preference
                    AppUtils.saveIntSetting(TaskListActivity.this, AppConstants.SORT_KEY, AppConstants.SORT_PRIORITY);
                }

                customOptionsDialog.hideDialog();
            }
        });

        customOptionsDialog.setOption4Listener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTaskPresenter.sortByStatus();

                    //  Save preference
                    AppUtils.saveIntSetting(TaskListActivity.this, AppConstants.SORT_KEY, AppConstants.SORT_STATUS);
                }

                customOptionsDialog.hideDialog();
            }
        });
        customOptionsDialog.showDialog();

    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }
}

