package com.mindtree.fsmmindtree.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.BaseActivity;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.task.UpdateRequestObject;
import com.mindtree.fsmmindtree.data.task.UpdateResponseObject;
import com.mindtree.fsmmindtree.presenter.TaskPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by sunilbagunji on 11/12/15.
 */
public class TaskDetailsActivity extends BaseActivity {
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtCustomerName)
    TextView txtCustomerName;
    @Bind(R.id.txtDate)
    TextView txtDate;
    @Bind(R.id.txtPriority)
    TextView txtPriority;
    @Bind(R.id.txtTime)
    TextView txtTime;
    @Bind(R.id.txtTaskTitle)
    TextView txtTaskTitle;
    @Bind(R.id.txtTaskDesc)
    TextView txtTaskDesc;
    @Bind(R.id.txtStatusTitle)
    TextView txtStatusTitle;
    @Bind(R.id.txtCustomerAddressTitle)
    TextView txtCustomerAddressTitle;
    @Bind(R.id.txtCustomerAddress)
    TextView txtCustomerAddress;
    @Bind(R.id.txtContactTitle)
    TextView txtContactTitle;
    @Bind(R.id.txtContact)
    TextView txtContact;
    @Bind(R.id.btnDecline)
    Button btnDecline;
    @Bind(R.id.btnOnHold)
    Button btnOnHold;
    @Bind(R.id.btnAssign)
    Button btnAssign;
    @Bind(R.id.txtStatusNew)
    TextView txtStatusNew;
    @Bind(R.id.txtStatusAssigned)
    TextView txtStatusAssigned;
    @Bind(R.id.txtInProgress)
    TextView txtInProgress;
    @Bind(R.id.txtStatusCompleted)
    TextView txtStatusCompleted;
    @Bind(R.id.txtAssignedTitle)
    TextView txtAssignedTitle;
    @Bind(R.id.edtTechnician)
    EditText edtTechnician;
    @Bind(R.id.edtComments)
    EditText edtComments;
    @Bind(R.id.imgUnassigned)
    ImageView imgUnassigned;
    @Bind(R.id.imgAssigned)
    ImageView imgAssigned;
    @Bind(R.id.imgInprogress)
    ImageView imgInprogress;
    @Bind(R.id.imgCompleted)
    ImageView imgCompleted;
    @Bind(R.id.llFooter)
    LinearLayout llFooter;
    @Bind(R.id.btnUpdate)
    Button btnUpdate;
    @Bind(R.id.llContact)
    LinearLayout llContact;
    @Bind(R.id.txtVisitTitle)
    TextView txtVisitTitle;
    @Bind(R.id.txtVisitDate)
    TextView txtVisitDate;
    @Bind(R.id.txtVisitTime)
    TextView txtVisitTime;
    @Bind(R.id.imgCall)
    ImageView imgCall;
    @Bind(R.id.imgLocation)
    ImageView imgLocation;
    @Bind(R.id.llVisitDateTime)
    LinearLayout llVisitDateTime;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetails);
        ButterKnife.bind(this);

        // Register as a subscriber
        bus.register(this);

        styleUi();

        switch (RuntimeData.sLoginResponseObject.getUsertype()) {
            case AppConstants.USERS.USER_SUPERVISOR:
                supervisorView();
                break;

            case AppConstants.USERS.USER_TECHNICAN:
                technicianView();
                break;
        }
    }

    private void commonView() {
        if (RuntimeData.sSelectedTask.getAssigneddate() != null) {
            String dateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(RuntimeData.sSelectedTask.getAssigneddate()));
            txtDate.setText(dateString);

            txtTitle.setText("TASK: " + String.valueOf(RuntimeData.sSelectedTask.getTaskid()));

            txtCustomerName.setText(RuntimeData.sSelectedTask.getCustomername());
            txtTaskDesc.setText(RuntimeData.sSelectedTask.getDescription());
            txtTaskDesc.setMovementMethod(new ScrollingMovementMethod());

            Integer p = RuntimeData.sSelectedTask.getPriority();
            setPriorityText(p);

            Long date = RuntimeData.sSelectedTask.getActualstartdate();
            if (date != null) {
                String dateString1 = new SimpleDateFormat("MM/dd/yyyy").format(date);
                txtDate.setText(dateString1);
            }

            setStatus(RuntimeData.sSelectedTask.getStatus());

            edtComments.setText(RuntimeData.sSelectedTask.getComments());
        }
    }

    private void technicianView() {
        commonView();

        //  Change to Assigned By
        txtAssignedTitle.setText("Assigned By");

        //  Remove contact details
        llContact.setVisibility(View.GONE);
        llVisitDateTime.setVisibility(View.VISIBLE);

        if (RuntimeData.sSelectedTask.getStatus() == AppConstants.STATUS_ASSIGNED) {
            btnAssign.setText("Start Task");

            btnAssign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateOrAssignTask(AppConstants.STATUS_INPROGRESS);
                }
            });
        } else if (RuntimeData.sSelectedTask.getStatus() == AppConstants.STATUS_INPROGRESS) {
            llFooter.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);

            btnUpdate.setText("Complete Task");

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateOrAssignTask(AppConstants.STATUS_COMPLETED);
                }
            });
        } else if (RuntimeData.sSelectedTask.getStatus() == AppConstants.STATUS_COMPLETED || RuntimeData.sSelectedTask.getStatus() == AppConstants.STATUS_UNASSIGNED) {
            llFooter.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
        }

        edtTechnician.setText(RuntimeData.sSelectedTask.getAssignedby());
    }

    private void supervisorView() {
        commonView();

        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(RuntimeData.sSelectedTask.getFloornumber());
        stringBuffer.append(", ");
        stringBuffer.append(RuntimeData.sSelectedTask.getBuildingname());
        stringBuffer.append("\n");
        stringBuffer.append(RuntimeData.sSelectedTask.getCustomeraddress());
        stringBuffer.append("\n");
        stringBuffer.append(RuntimeData.sSelectedTask.getCity());
        stringBuffer.append(", ");
        stringBuffer.append(RuntimeData.sSelectedTask.getState());

        txtCustomerAddress.setText(stringBuffer.toString());
        txtCustomerAddress.setMovementMethod(new ScrollingMovementMethod());

        if (RuntimeData.sSelectedTask.getStatus() != AppConstants.STATUS_UNASSIGNED) {
            llFooter.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
        }

        txtPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog();
            }
        });

        edtTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LocationObject l1 = new LocationObject(12.9702, 77.5603, "Bangalore");
//                LocationObject l2 = new LocationObject(12.9667, 77.5667, "Mysore");

//                ArrayList<LocationObject> locationList = new ArrayList<LocationObject>();
//                locationList.add(l1);
//                locationList.add(l2);

                Intent intent = new Intent(TaskDetailsActivity.this, TechnicianListActivity.class);
//                intent.putExtra("locationlist", locationList);
                startActivityForResult(intent, 0);
            }
        });

        edtTechnician.setText(RuntimeData.sSelectedTask.getAssignedto());

        txtCustomerAddressTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = RuntimeData.sSelectedTask.getLatitude();
                double longt = RuntimeData.sSelectedTask.getLongitude();

                Intent intent = new Intent(TaskDetailsActivity.this, ShowRouteActivity.class);
                intent.putExtra("customer", RuntimeData.sSelectedTask.getCustomername());
                intent.putExtra("custlat", lat);
                intent.putExtra("custlongt", longt);

                startActivity(intent);
            }
        });

        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat = RuntimeData.sSelectedTask.getLatitude();
                double longt = RuntimeData.sSelectedTask.getLongitude();

                Intent intent = new Intent(TaskDetailsActivity.this, ShowRouteActivity.class);
                intent.putExtra("customer", RuntimeData.sSelectedTask.getCustomername());
                intent.putExtra("custlat", lat);
                intent.putExtra("custlongt", longt);

                startActivity(intent);
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  TODO:  extract contact number from response and add action here.
            }
        });

        btnAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrAssignTask(AppConstants.STATUS_ASSIGNED);
            }
        });
    }

    private void updateOrAssignTask(int type) {
        UpdateRequestObject requestObject = new UpdateRequestObject();
        requestObject.setActualstartdate(System.currentTimeMillis());
        requestObject.setActualenddate(System.currentTimeMillis());
        requestObject.setAssignedby(RuntimeData.sLoginResponseObject.getId());
        requestObject.setAssigneddate(System.currentTimeMillis());
        requestObject.setComments(RuntimeData.sSelectedTask.getComments());
        requestObject.setPlannedstartdate(System.currentTimeMillis());
        requestObject.setPlannedenddate(System.currentTimeMillis());
        requestObject.setTaskid(RuntimeData.sSelectedTask.getTaskid());
        requestObject.setPriority(RuntimeData.sSelectedTask.getPriority());
        requestObject.setComments(edtComments.getText().toString());
        requestObject.setStatus(type);

        if (RuntimeData.sSelectedTask.getStatus() == AppConstants.STATUS_UNASSIGNED) {
            requestObject.setType(AppConstants.TYPE_ASSIGN);

            if (RuntimeData.sSelectedTechician != null) {
                requestObject.setAssignedto(RuntimeData.sSelectedTechician.getId());

                showProgress("Loading", "Assiging task..");

                TaskPresenter presenter = new TaskPresenter(TaskDetailsActivity.this);
                presenter.callAssignTaskApi(requestObject);
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Please assign the task to a technician.", Snackbar.LENGTH_LONG)
                        .show();
            }
        } else {
            requestObject.setType(AppConstants.TYPE_UPDATE);

            showProgress("Loading", "Updating task..");

            TaskPresenter presenter = new TaskPresenter(TaskDetailsActivity.this);
            presenter.callAssignTaskApi(requestObject);
        }
    }

    private void setPriorityText(Integer p) {
        int c = 1;
        String priority = "";

        if (p != null) {
                switch (p) {
                    case 1:
                        priority = "HIGH";
                        c = ContextCompat.getColor(this, R.color.color_priority);
                        break;

                    case 2:
                        priority = "MEDIUM";
                        c = ContextCompat.getColor(this, R.color.orange);
                        break;

                    case 3:
                        priority = "NORMAL";
                        c = ContextCompat.getColor(this, R.color.color_dark_grey);
                        break;

                    default:
                        priority = "MEDIUM";
                        c = ContextCompat.getColor(this, R.color.orange);
                        break;
                }
            }
            String priorityTitle = "Priority: ";
            Spannable WordtoSpan = new SpannableString(priorityTitle + priority);
            WordtoSpan.setSpan(new ForegroundColorSpan(c), priorityTitle.length(), priorityTitle.length() + priority.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            txtPriority.setText(WordtoSpan);

    }

    private void styleUi() {
        txtTitle.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

        txtCustomerName.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));
        txtDate.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));
        txtTaskTitle.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
        txtTaskDesc.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));

        txtStatusTitle.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

        btnDecline.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
        btnOnHold.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
        btnAssign.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

        txtTaskDesc.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onEvent(UpdateResponseObject updateResponseObject) {
        hideProgress();

        if (updateResponseObject != null) {
            if (updateResponseObject.getResult()) {
                Toast.makeText(this, "Task Updated Successfully.", Toast.LENGTH_LONG).show();

                //  If user is technican and task status is assigned, recreate activity after status change, else finish it.
                if (RuntimeData.sLoginResponseObject.getUsertype() == AppConstants.USERS.USER_TECHNICAN && RuntimeData.sSelectedTask.getStatus() == AppConstants.STATUS_ASSIGNED) {
                    recreate();
                } else {
                    finish();
                }

            } else {
                Toast.makeText(this, "Task Update Failed.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Task Updated Failed!!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (RuntimeData.sSelectedTechician != null) {
                    edtTechnician.setText(RuntimeData.sSelectedTechician.getUsername());
                }
                break;
        }
    }

    private void showOptionDialog() {
        AlertDialog actions;

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
//        builder.setTitle("Set Priority");

        String[] options = {"High", "Medium", "Low"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        RuntimeData.sSelectedTask.setPriority(1);
                        break;
                    case 1:
                        RuntimeData.sSelectedTask.setPriority(2);
                        break;
                    case 2:
                        RuntimeData.sSelectedTask.setPriority(3);
                        break;
                    default:
                        break;
                }

                setPriorityText(which + 1);
            }
        });
        builder.setNegativeButton("Cancel", null);

        actions = builder.create();
        actions.show();
    }

    private void setStatus(int status) {
        switch (status) {
            case AppConstants.STATUS_UNASSIGNED:
                txtStatusNew.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
                break;

            case AppConstants.STATUS_ASSIGNED:
                imgUnassigned.setImageResource(R.drawable.status_completed);
                imgAssigned.setImageResource(R.drawable.status_in_progress);

                txtStatusAssigned.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

                break;

            case AppConstants.STATUS_INPROGRESS:
                imgUnassigned.setImageResource(R.drawable.status_completed);
                imgAssigned.setImageResource(R.drawable.status_completed);
                imgInprogress.setImageResource(R.drawable.status_in_progress);

                txtInProgress.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

                break;

            case AppConstants.STATUS_COMPLETED:
                imgUnassigned.setImageResource(R.drawable.status_completed);
                imgAssigned.setImageResource(R.drawable.status_completed);
                imgInprogress.setImageResource(R.drawable.status_completed);
                imgCompleted.setImageResource(R.drawable.status_completed);

                txtStatusCompleted.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

                break;

        }
    }
}
