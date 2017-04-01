package com.mindtree.fsmmindtree.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mindtree.fsmmindtree.GCMIntentService;
import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConfig;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.BaseActivity;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.data.LoginRequestObject;
import com.mindtree.fsmmindtree.data.LoginResponseObject;
import com.mindtree.fsmmindtree.data.PushRequestObject;
import com.mindtree.fsmmindtree.data.PushResponseObject;
import com.mindtree.fsmmindtree.helper.DialogHelper;
import com.mindtree.fsmmindtree.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.imgSupervisor)
    ImageButton imgSupervisor;
    @Bind(R.id.txtSupervisor)
    TextView txtSupervisor;
    @Bind(R.id.imgTechnican)
    ImageButton imgTechnican;
    @Bind(R.id.txtTechnician)
    TextView txtTechnician;
    @Bind(R.id.imgCustomer)
    ImageButton imgCustomer;
    @Bind(R.id.txtCustomer)
    TextView txtCustomer;
    @Bind(R.id.txtRoleMsg)
    TextView txtRoleMsg;
    @Bind(R.id.edtUsername)
    EditText edtUsername;
    @Bind(R.id.edtPassword)
    EditText edtPassword;
    @Bind(R.id.chkRemember)
    CheckBox chkRemember;
    @Bind(R.id.txtForgotPassword)
    TextView txtForgotPassword;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.txtRegMsg)
    TextView txtRegMsg;
    @Bind(R.id.btnRegister)
    Button btnRegister;
    private LoginPresenter mPresenter;
    private EventBus bus = EventBus.getDefault();
    private LoginRequestObject requestObject = new LoginRequestObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        styleUi();

        //  added for testing
        edtPassword.setText("sup1");

        // Register as a subscriber
        bus.register(this);

        mPresenter = new LoginPresenter(this, requestObject);
        //  Check and set user type.
        int userType = mPresenter.getUserType(this);
        setUserType(userType);

        //  Set remember me.
        String username = mPresenter.getUsername(this);
        if (username != null) {
            edtUsername.setText(username);
            edtPassword.requestFocus();

            chkRemember.setChecked(true);
        } else {
            chkRemember.setChecked(false);
        }
    }

    private void styleUi() {
//        Snackbar.make(findViewById(android.R.id.content), "Styling UI...", Snackbar.LENGTH_LONG)
//                .show();

        txtTitle.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

        txtSupervisor.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));
        txtTechnician.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));
        txtCustomer.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));

        txtRoleMsg.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

        txtForgotPassword.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));
        chkRemember.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));

        btnLogin.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
        txtRegMsg.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));

        btnRegister.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
    }

    @OnClick(R.id.btnLogin)
    public void loginButtonClicked() {
        requestObject.setUsername(edtUsername.getText().toString());
        requestObject.setPassword(edtPassword.getText().toString());
        requestObject.setUsertype(String.valueOf(mPresenter.getUserType(this)));

        //  Remember username.
        if (chkRemember.isChecked()) {
            mPresenter.setUsername(this, edtUsername.getText().toString());
        }

        showProgress("Login", "Logging in...");
        mPresenter.loginUser();
    }

    @OnClick(R.id.btnRegister)
    public void registerButtonClicke() {
        mPresenter.navigateToRegister();
    }

    @OnClick(R.id.imgCustomer)
    public void customerSelected() {
        setUserType(AppConstants.USERS.USER_CUSTOMER);

        requestObject.setUsertype(AppConfig.USER_CUSTOMER);
        mPresenter.setUserType(this, AppConstants.USERS.USER_CUSTOMER);
    }

    @OnClick(R.id.imgTechnican)
    public void technicianSelected() {
        setUserType(AppConstants.USERS.USER_TECHNICAN);

        requestObject.setUsertype(AppConfig.USER_TECHNICIAN);
        mPresenter.setUserType(this, AppConstants.USERS.USER_TECHNICAN);
    }

    @OnClick(R.id.imgSupervisor)
    public void supervisorSelected() {
        setUserType(AppConstants.USERS.USER_SUPERVISOR);

        requestObject.setUsertype(AppConfig.USER_SUPERVISOR);
        mPresenter.setUserType(this, AppConstants.USERS.USER_SUPERVISOR);
    }

    @OnCheckedChanged(R.id.chkRemember)
    public void rememberMe(boolean checked) {
        if (checked) {
            mPresenter.setUsername(this, edtUsername.getText().toString());
        } else {
            mPresenter.setUsername(this, null);
        }
    }

    public void onEvent(LoginResponseObject loginResponseObject) {
        hideProgress();

        if(loginResponseObject != null) {
            if (loginResponseObject.getAccesstoken() != null) {
                // Register to push notification only when logged in as zone
                // controller.
                // Check and register for push notification.
                if(RuntimeData.sLoginResponseObject.getUsertype() == 2) {
                    String pushRegId = AppUtils.getStringSetting(this, AppConstants.GCM_ID);

                    if (pushRegId == null) {
                        Toast.makeText(this, "Getting GCM id...", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(this, GCMIntentService.class);
                        startService(intent);
                    } else {
                        Toast.makeText(this, "GCM id already exists..: " + pushRegId, Toast.LENGTH_LONG).show();
                    }
                }

                mPresenter.navigateToHome();

                finish();
            } else {
                edtPassword.setText("");

                DialogHelper.showErrorDialog(this, "Login Error", "Login Failed");
            }
        }
    }

    public void onEvent(PushRequestObject pushRequestObject) {
        pushRequestObject.setUsername(requestObject.getUsername());
        mPresenter.callRegPushApi(pushRequestObject);
    }

    public void onEvent(PushResponseObject pushResponseObject) {
        if(pushResponseObject.getResult()) {
            Toast.makeText(this, "Push registration successful.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Push registration failed.", Toast.LENGTH_LONG).show();
        }
    }

    public void setUserType(int userType) {
        switch (userType) {
            case AppConstants.USERS.USER_SUPERVISOR:
                imgCustomer.setBackgroundColor(getResources().getColor(R.color.color_bg_all));
                imgSupervisor.setBackgroundColor(getResources().getColor(R.color.color_bg_supervisor));
                imgTechnican.setBackgroundColor(getResources().getColor(R.color.color_bg_all));

                break;

            case AppConstants.USERS.USER_TECHNICAN:
                imgCustomer.setBackgroundColor(getResources().getColor(R.color.color_bg_all));
                imgSupervisor.setBackgroundColor(getResources().getColor(R.color.color_bg_all));
                imgTechnican.setBackgroundColor(getResources().getColor(R.color.color_bg_technician));
                break;

            case AppConstants.USERS.USER_CUSTOMER:
                imgCustomer.setBackgroundColor(getResources().getColor(R.color.color_bg_customer));
                imgSupervisor.setBackgroundColor(getResources().getColor(R.color.color_bg_all));
                imgTechnican.setBackgroundColor(getResources().getColor(R.color.color_bg_all));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }
}
