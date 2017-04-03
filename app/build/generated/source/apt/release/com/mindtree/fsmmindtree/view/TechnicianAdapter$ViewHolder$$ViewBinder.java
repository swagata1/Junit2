// Generated code from Butter Knife. Do not modify!
package com.mindtree.fsmmindtree.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TechnicianAdapter$ViewHolder$$ViewBinder<T extends com.mindtree.fsmmindtree.view.TechnicianAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624098, "field 'imgSupervisor'");
    target.imgSupervisor = finder.castView(view, 2131624098, "field 'imgSupervisor'");
    view = finder.findRequiredView(source, 2131624213, "field 'txtTechnicianName'");
    target.txtTechnicianName = finder.castView(view, 2131624213, "field 'txtTechnicianName'");
    view = finder.findRequiredView(source, 2131624214, "field 'txtTechnicianSkill'");
    target.txtTechnicianSkill = finder.castView(view, 2131624214, "field 'txtTechnicianSkill'");
  }

  @Override public void unbind(T target) {
    target.imgSupervisor = null;
    target.txtTechnicianName = null;
    target.txtTechnicianSkill = null;
  }
}
