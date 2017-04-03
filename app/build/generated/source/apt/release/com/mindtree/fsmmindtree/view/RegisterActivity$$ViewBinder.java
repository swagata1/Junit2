// Generated code from Butter Knife. Do not modify!
package com.mindtree.fsmmindtree.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.mindtree.fsmmindtree.view.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624094, "field 'txtTitle'");
    target.txtTitle = finder.castView(view, 2131624094, "field 'txtTitle'");
    view = finder.findRequiredView(source, 2131624093, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624093, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624114, "field 'edtFirstname'");
    target.edtFirstname = finder.castView(view, 2131624114, "field 'edtFirstname'");
    view = finder.findRequiredView(source, 2131624115, "field 'edtLastname'");
    target.edtLastname = finder.castView(view, 2131624115, "field 'edtLastname'");
    view = finder.findRequiredView(source, 2131624116, "field 'edtEmail'");
    target.edtEmail = finder.castView(view, 2131624116, "field 'edtEmail'");
    view = finder.findRequiredView(source, 2131624105, "field 'edtUsername'");
    target.edtUsername = finder.castView(view, 2131624105, "field 'edtUsername'");
    view = finder.findRequiredView(source, 2131624106, "field 'edtPassword'");
    target.edtPassword = finder.castView(view, 2131624106, "field 'edtPassword'");
    view = finder.findRequiredView(source, 2131624119, "field 'radioSupervisor' and method 'supervisorSelected'");
    target.radioSupervisor = finder.castView(view, 2131624119, "field 'radioSupervisor'");
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.supervisorSelected(p1);
        }
      });
    view = finder.findRequiredView(source, 2131624120, "field 'radioTechnician' and method 'technicianSelected'");
    target.radioTechnician = finder.castView(view, 2131624120, "field 'radioTechnician'");
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.technicianSelected(p1);
        }
      });
    view = finder.findRequiredView(source, 2131624121, "field 'radioCustomer' and method 'customerSelected'");
    target.radioCustomer = finder.castView(view, 2131624121, "field 'radioCustomer'");
    ((android.widget.CompoundButton) view).setOnCheckedChangeListener(
      new android.widget.CompoundButton.OnCheckedChangeListener() {
        @Override public void onCheckedChanged(
          android.widget.CompoundButton p0,
          boolean p1
        ) {
          target.customerSelected(p1);
        }
      });
    view = finder.findRequiredView(source, 2131624118, "field 'radioGrpUserType'");
    target.radioGrpUserType = finder.castView(view, 2131624118, "field 'radioGrpUserType'");
    view = finder.findRequiredView(source, 2131624111, "field 'btnRegister' and method 'registerButtonClicked'");
    target.btnRegister = finder.castView(view, 2131624111, "field 'btnRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.registerButtonClicked();
        }
      });
    view = finder.findRequiredView(source, 2131624117, "field 'edtMobile'");
    target.edtMobile = finder.castView(view, 2131624117, "field 'edtMobile'");
  }

  @Override public void unbind(T target) {
    target.txtTitle = null;
    target.toolbar = null;
    target.edtFirstname = null;
    target.edtLastname = null;
    target.edtEmail = null;
    target.edtUsername = null;
    target.edtPassword = null;
    target.radioSupervisor = null;
    target.radioTechnician = null;
    target.radioCustomer = null;
    target.radioGrpUserType = null;
    target.btnRegister = null;
    target.edtMobile = null;
  }
}
