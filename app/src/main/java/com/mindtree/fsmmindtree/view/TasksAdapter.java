package com.mindtree.fsmmindtree.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.task.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class TasksAdapter extends ArrayAdapter<Task> {
    private Context mContext;
    private ArrayList<Task> mTaskList;

    public TasksAdapter(Context context, ArrayList<Task> taskList) {
        super(context, 0, taskList);

        mContext = context;
        mTaskList = taskList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder taskViewHolder = null;

        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_task, parent, false);

            taskViewHolder = new ViewHolder();

            taskViewHolder.imgLocation = (ImageView) convertView.findViewById(R.id.imgLocation);

            taskViewHolder.txtTaskId = (TextView) convertView.findViewById(R.id.txtId);
            taskViewHolder.txtCustomerAddress = (TextView) convertView.findViewById(R.id.txtName);
            taskViewHolder.txtDesc = (TextView) convertView.findViewById(R.id.txtDesc);
            taskViewHolder.txtStatusTitle = (TextView) convertView.findViewById(R.id.txtStatusTitle);
            taskViewHolder.txtPriorityTitle = (TextView) convertView.findViewById(R.id.txtPriorityTitle);
            taskViewHolder.txtStatus = (TextView) convertView.findViewById(R.id.txtStatus);
            taskViewHolder.txtPriority = (TextView) convertView.findViewById(R.id.txtPriority);
            taskViewHolder.txtDate = (TextView) convertView.findViewById(R.id.txtIssueDate);

            taskViewHolder.txtTaskId = (TextView) convertView.findViewById(R.id.txtId);
            taskViewHolder.txtTaskId = (TextView) convertView.findViewById(R.id.txtId);

            taskViewHolder.txtTaskId.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_BOLD));
            taskViewHolder.txtDesc.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_NORMAL));

            taskViewHolder.txtCustomerAddress.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_NORMAL));
            taskViewHolder.txtPriorityTitle.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_BOLD));
            taskViewHolder.txtStatusTitle.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_BOLD));
            taskViewHolder.txtPriority.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_NORMAL));
            taskViewHolder.txtStatus.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_NORMAL));
            taskViewHolder.txtDate.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_NORMAL));

            if (RuntimeData.sLoginResponseObject.getUsertype() != AppConstants.USERS.USER_TECHNICAN) {
                taskViewHolder.imgLocation.setVisibility(View.GONE);
            }

            convertView.setTag(taskViewHolder);
        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            taskViewHolder = (ViewHolder) convertView.getTag();
        }

        //  add here
        taskViewHolder.txtTaskId.setText("TASK: " + String.valueOf(mTaskList.get(position).getTaskid()));
        taskViewHolder.txtCustomerAddress.setText(mTaskList.get(position).getCustomername() + " | " + mTaskList.get(position).getCity());
        taskViewHolder.txtDesc.setText(mTaskList.get(position).getDescription());

        taskViewHolder.imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTaskList.get(position).getLatitude();
            }
        });

        String priority = "";

        Integer p = mTaskList.get(position).getPriority();

        if (p != null) {
            switch (p) {
                case 1:
                    priority = "HIGH";
                    taskViewHolder.txtPriority.setTextColor(mContext.getResources().getColor(R.color.color_priority));
                    break;

                case 2:
                    priority = "MEDIUM";
                    taskViewHolder.txtPriority.setTextColor(mContext.getResources().getColor(R.color.orange));
                    break;

                case 3:
                    priority = "NORMAL";
                    taskViewHolder.txtPriority.setTextColor(mContext.getResources().getColor(R.color.color_dark_grey));
                    break;

                default:
                    priority = "MEDIUM";
                    taskViewHolder.txtPriority.setTextColor(mContext.getResources().getColor(R.color.orange));
                    break;
            }
        } else {
            priority = "MEDIUM";
            taskViewHolder.txtPriority.setTextColor(mContext.getResources().getColor(R.color.orange));
        }

        taskViewHolder.imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = mTaskList.get(position).getLatitude();
                double longt = mTaskList.get(position).getLongitude();


                Intent intent = new Intent(mContext, ShowRouteActivity.class);
                intent.putExtra("customer", mTaskList.get(position).getCustomername());
                intent.putExtra("custlat", lat);
                intent.putExtra("custlongt", longt);

                mContext.startActivity(intent);
            }
        });

        taskViewHolder.txtPriority.setText(priority);

//        1:  “unassigned”
//        2: “assigned”
//        3: “in progress”
//        4: “completed”

        String status = "Unassigned";
        Integer s = mTaskList.get(position).getStatus();

        if (s != null) {
            switch (s) {
                case 1:
                    status = "Unassigned";
                    break;

                case 2:
                    status = "Assigned";
                    break;

                case 3:
                    status = "In Progress";
                    break;

                case 4:
                    status = "Completed";
                    break;
            }
            taskViewHolder.txtStatus.setText(status);
        }

        Long date = mTaskList.get(position).getActualstartdate();
        if (date != null) {
            String dateString = new SimpleDateFormat("MM/dd/yyyy").format(date);
            taskViewHolder.txtDate.setText(dateString);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imgLocation;
        TextView txtTaskId;
        TextView txtCustomerAddress;
        TextView txtDesc;
        TextView txtPriorityTitle;
        TextView txtPriority;
        TextView txtStatusTitle;
        TextView txtStatus;
        TextView txtDate;
    }
}
