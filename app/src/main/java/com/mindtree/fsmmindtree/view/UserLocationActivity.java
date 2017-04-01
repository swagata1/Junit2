package com.mindtree.fsmmindtree.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.BaseActivity;
import com.mindtree.fsmmindtree.data.location.UserLocationObject;
import com.mindtree.fsmmindtree.presenter.UserLocationPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by sunilbagunji on 12/12/15.
 */
public class UserLocationActivity extends BaseActivity {

    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private UserLocationPresenter mPresenter;
    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // Register as a subscriber
        bus.register(this);


        mPresenter = new UserLocationPresenter(this);
        mPresenter.getUserLocationList();
    }

    public void onEvent(UserLocationObject userLocationObject) {
       Log.i("UserLocationObject", userLocationObject.getResult().get(0).getAssignedto());
    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }
}
