// Generated code from Butter Knife. Do not modify!
package com.mindtree.fsmmindtree.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TaskListActivity$$ViewBinder<T extends com.mindtree.fsmmindtree.view.TaskListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624094, "field 'txtTitle'");
    target.txtTitle = finder.castView(view, 2131624094, "field 'txtTitle'");
    view = finder.findRequiredView(source, 2131624093, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624093, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624157, "field 'tabs'");
    target.tabs = finder.castView(view, 2131624157, "field 'tabs'");
    view = finder.findRequiredView(source, 2131624158, "field 'pager'");
    target.pager = finder.castView(view, 2131624158, "field 'pager'");
    view = finder.findRequiredView(source, 2131624156, "field 'imgFilter'");
    target.imgFilter = finder.castView(view, 2131624156, "field 'imgFilter'");
  }

  @Override public void unbind(T target) {
    target.txtTitle = null;
    target.toolbar = null;
    target.tabs = null;
    target.pager = null;
    target.imgFilter = null;
  }
}
