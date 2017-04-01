package com.mindtree.fsmmindtree.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.BaseListFragment;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.task.Task;
import com.mindtree.fsmmindtree.data.task.TaskResponseObject;
import com.mindtree.fsmmindtree.presenter.TaskPresenter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class TaskListFragment extends BaseListFragment implements AdapterView.OnItemClickListener {
    private TaskPresenter mPresenter;
    private EventBus bus = EventBus.getDefault();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_tasklist, container,
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        showProgress("Getting Tasks", "Loading...");

        mPresenter = new TaskPresenter(getActivity());
        mPresenter.getTasksList();
    }

    public void onEvent(TaskResponseObject taskResponseObject) {
        hideProgress();

        if (taskResponseObject != null) {
            RuntimeData.sTaskList = (ArrayList<Task>) taskResponseObject.getTasks();

            //  Get sort type
            int sortType = AppUtils.getIntSetting(getActivity(), AppConstants.SORT_KEY);

            if (sortType == -1) {
                sortType = AppConstants.SORT_DATE;
            }

            switch (sortType) {
                case AppConstants.SORT_DATE:
                    mPresenter.sortByDate();
                    break;
                case AppConstants.SORT_TYPE:
                    mPresenter.sortByType();
                    break;
                case AppConstants.SORT_PRIORITY:
                    mPresenter.sortByPriority();
                    break;
                case AppConstants.SORT_STATUS:
                    mPresenter.sortByStatus();
                    break;
            }

            bus.post(new Integer( RuntimeData.sTaskList.size()));

            TasksAdapter adapter = new TasksAdapter(getActivity(),  RuntimeData.sTaskList);
            setListAdapter(adapter);
        }
    }

    public void onEvent(ArrayList<Task> list) {
        if (list != null) {
            TasksAdapter adapter = new TasksAdapter(getActivity(), list);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RuntimeData.sSelectedTask =  RuntimeData.sTaskList.get(position);

        Intent intent = new Intent(getActivity(), TaskDetailsActivity.class);
        startActivityForResult(intent, 0);
    }


    @Override
    public void onDestroyView() {
        // Unregister
        bus.unregister(this);
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
