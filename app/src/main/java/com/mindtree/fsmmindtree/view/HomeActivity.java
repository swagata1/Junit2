package com.mindtree.fsmmindtree.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.BaseActivity;
import com.mindtree.fsmmindtree.data.location.LocationObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class HomeActivity extends BaseActivity {


    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtTest)
    TextView txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        txtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationObject l1 = new LocationObject(12.9702, 77.5603, "Bangalore");
                LocationObject l2 = new LocationObject(12.9667, 77.5667, "Mysore");

            ArrayList<LocationObject> locationList = new ArrayList<LocationObject>();
            locationList.add(l1);
            locationList.add(l2);

            Intent intent = new Intent(HomeActivity.this, TechnicianListActivity.class);
            intent.putExtra("locationlist", locationList);
            startActivity(intent);
        }
        });

    }
}
