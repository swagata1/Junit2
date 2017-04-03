// Generated code from Butter Knife. Do not modify!
package com.mindtree.fsmmindtree.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeActivity$$ViewBinder<T extends com.mindtree.fsmmindtree.view.HomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624094, "field 'txtTitle'");
    target.txtTitle = finder.castView(view, 2131624094, "field 'txtTitle'");
    view = finder.findRequiredView(source, 2131624093, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624093, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624095, "field 'txtTest'");
    target.txtTest = finder.castView(view, 2131624095, "field 'txtTest'");
  }

  @Override public void unbind(T target) {
    target.txtTitle = null;
    target.toolbar = null;
    target.txtTest = null;
  }
}
