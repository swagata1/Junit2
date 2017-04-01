package com.mindtree.fsmmindtree.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.BaseListFragment;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.technician.Result;
import com.mindtree.fsmmindtree.data.technician.TechnicianResponseObject;
import com.mindtree.fsmmindtree.presenter.TechnicianPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by sunilbagunji on 12/12/15.
 */
public class TechnicianListFragment extends BaseListFragment implements OnItemClickListener {
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private TechnicianPresenter mPresenter;
    private EventBus bus = EventBus.getDefault();
    private TechnicianAdapter mAdapter;

    private TechnicianResponseObject mTechnicianResponseObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_technicianlist, container,
                false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());

        // Register as a subscriber
        bus.register(this);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        RuntimeData.sSelectedTechician = mTechnicianResponseObject.getResult().get(position);

        getActivity().setResult(getActivity().RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showProgress("Get Technician List", "Loading...");
        mPresenter = new TechnicianPresenter(getActivity());
        mPresenter.getTechnicianList();
    }

    public void onEvent(TechnicianResponseObject technicianResponseObject) {
        hideProgress();

        RuntimeData.sTechicianList = (ArrayList<Result>) technicianResponseObject.getResult();

        bus.post((ArrayList<Result>) technicianResponseObject.getResult());

        mTechnicianResponseObject = technicianResponseObject;

        mAdapter = new TechnicianAdapter(getActivity(), (ArrayList<Result>) technicianResponseObject.getResult());
        setListAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        // Unregister
        bus.unregister(this);
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
