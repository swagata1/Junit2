package com.mindtree.fsmmindtree.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConfig;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.common.BaseActivity;
import com.mindtree.fsmmindtree.data.RegisterRequestObject;
import com.mindtree.fsmmindtree.data.RegisterResponseObject;
import com.mindtree.fsmmindtree.helper.DialogHelper;
import com.mindtree.fsmmindtree.presenter.RegisterPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class RegisterActivity extends BaseActivity {
    @Bind(R.id.txtTitle)
    TextView txtTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edtFirstname)
    EditText edtFirstname;
    @Bind(R.id.edtLastname)
    EditText edtLastname;
    @Bind(R.id.edtEmail)
    EditText edtEmail;
    @Bind(R.id.edtUsername)
    EditText edtUsername;
    @Bind(R.id.edtPassword)
    EditText edtPassword;
    @Bind(R.id.radioSupervisor)
    RadioButton radioSupervisor;
    @Bind(R.id.radioTechnician)
    RadioButton radioTechnician;
    @Bind(R.id.radioCustomer)
    RadioButton radioCustomer;
    @Bind(R.id.radioGrpUserType)
    RadioGroup radioGrpUserType;
    @Bind(R.id.btnRegister)
    Button btnRegister;
    @Bind(R.id.edtMobile)
    EditText edtMobile;

    private RegisterPresenter mPresenter;
    private EventBus bus = EventBus.getDefault();
    RegisterRequestObject requestObject = new RegisterRequestObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        styleUi();

        // Register as a subscriber
        bus.register(this);
    }

    private void styleUi() {
        txtTitle.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));

        edtFirstname.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));

        radioSupervisor.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));
        radioTechnician.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));
        radioCustomer.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_NORMAL));

        btnRegister.setTypeface(AppUtils.getCustomFont(this, AppConstants.Fonts.FONT_BOLD));
    }

    @OnClick(R.id.btnRegister)
    public void registerButtonClicked() {
        requestObject.setUsername(edtUsername.getText().toString());
        requestObject.setEmail(edtEmail.getText().toString());

        try {
            requestObject.setMobilenum(edtMobile.getText().toString());
        } catch (Exception e) {
            requestObject.setMobilenum("");
        }

        requestObject.setFirstname(edtFirstname.getText().toString());
        requestObject.setLastname(edtLastname.getText().toString());
        requestObject.setPassword(edtPassword.getText().toString());

        showProgress("Register", "Registering...");

        mPresenter = new RegisterPresenter(requestObject);
        mPresenter.registerUser();
    }

    @OnCheckedChanged(R.id.radioCustomer)
    public void customerSelected(boolean checked) {
        if(checked) {
            requestObject.setUsertype(AppConfig.USER_CUSTOMER);
        }
    }

    @OnCheckedChanged(R.id.radioSupervisor)
    public void supervisorSelected(boolean checked) {
        if(checked) {
            requestObject.setUsertype(AppConfig.USER_SUPERVISOR);
        }
    }

    @OnCheckedChanged(R.id.radioTechnician)
    public void technicianSelected(boolean checked) {
        if(checked) {
            requestObject.setUsertype(AppConfig.USER_TECHNICIAN);
        }
    }

    public void onEvent(RegisterResponseObject registerResponseObject) {
        hideProgress();

        if(registerResponseObject != null) {
            if (registerResponseObject.getResult()) {
                Snackbar.make(findViewById(android.R.id.content), "Registration Successful!", Snackbar.LENGTH_LONG)
                        .show();

                finish();
            } else {
                DialogHelper.showErrorDialog(this, "Registration Error", "Registration Failed.");
            }
        }
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//        startActivity(intent);

        moveTaskToBack (true);
    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }
}
