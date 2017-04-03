// Generated code from Butter Knife. Do not modify!
package com.mindtree.fsmmindtree.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.mindtree.fsmmindtree.view.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624094, "field 'txtTitle'");
    target.txtTitle = finder.castView(view, 2131624094, "field 'txtTitle'");
    view = finder.findRequiredView(source, 2131624093, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624093, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624098, "field 'imgSupervisor' and method 'supervisorSelected'");
    target.imgSupervisor = finder.castView(view, 2131624098, "field 'imgSupervisor'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.supervisorSelected();
        }
      });
    view = finder.findRequiredView(source, 2131624099, "field 'txtSupervisor'");
    target.txtSupervisor = finder.castView(view, 2131624099, "field 'txtSupervisor'");
    view = finder.findRequiredView(source, 2131624100, "field 'imgTechnican' and method 'technicianSelected'");
    target.imgTechnican = finder.castView(view, 2131624100, "field 'imgTechnican'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.technicianSelected();
        }
      });
    view = finder.findRequiredView(source, 2131624101, "field 'txtTechnician'");
    target.txtTechnician = finder.castView(view, 2131624101, "field 'txtTechnician'");
    view = finder.findRequiredView(source, 2131624102, "field 'imgCustomer' and method 'customerSelected'");
    target.imgCustomer = finder.castView(view, 2131624102, "field 'imgCustomer'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.customerSelected();
        }
      });
    view = finder.findRequiredView(source, 2131624103, "field 'txtCustomer'");
    target.txtCustomer = finder.castView(view, 2131624103, "field 'txtCustomer'");
    view = finder.findRequiredView(source, 2131624104, "field 'txtRoleMsg'");
    target.txtRoleMsg = finder.castView(view, 2131624104, "field 'txtRoleMsg'");
    view = finder.findRequiredView(source, 2131624105, "field 'edtUsername'");
    target.edtUsername = finder.castView(view, 2131624105, "field 'edtUsername'");
    view = finder.findRequiredView(source, 2131624106, "field 'edtPassword'");
    target.edtPassword = finder.castView(view, 2131624106, "field 'edtPassword'");
    view = finder.findRequiredView(source, 2131624107, "field 'chkRemember' and method 'rememberMe'");
    target.chkRemember = finder.castView(view, 2131624107, "field 'chkRemember'");
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.rememberMe(p1);
        }
      });
    view = finder.findRequiredView(source, 2131624108, "field 'txtForgotPassword'");
    target.txtForgotPassword = finder.castView(view, 2131624108, "field 'txtForgotPassword'");
    view = finder.findRequiredView(source, 2131624109, "field 'btnLogin' and method 'loginButtonClicked'");
    target.btnLogin = finder.castView(view, 2131624109, "field 'btnLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.loginButtonClicked();
        }
      });
    view = finder.findRequiredView(source, 2131624110, "field 'txtRegMsg'");
    target.txtRegMsg = finder.castView(view, 2131624110, "field 'txtRegMsg'");
    view = finder.findRequiredView(source, 2131624111, "field 'btnRegister' and method 'registerButtonClicke'");
    target.btnRegister = finder.castView(view, 2131624111, "field 'btnRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.registerButtonClicke();
        }
      });
  }

  @Override public void unbind(T target) {
    target.txtTitle = null;
    target.toolbar = null;
    target.imgSupervisor = null;
    target.txtSupervisor = null;
    target.imgTechnican = null;
    target.txtTechnician = null;
    target.imgCustomer = null;
    target.txtCustomer = null;
    target.txtRoleMsg = null;
    target.edtUsername = null;
    target.edtPassword = null;
    target.chkRemember = null;
    target.txtForgotPassword = null;
    target.btnLogin = null;
    target.txtRegMsg = null;
    target.btnRegister = null;
  }
}
